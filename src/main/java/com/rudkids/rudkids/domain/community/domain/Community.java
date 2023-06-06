package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.communityLike.domain.CommunityLike;
import com.rudkids.rudkids.domain.user.domain.User;
import com.rudkids.rudkids.domain.user.exception.DifferentUserException;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_community")
public class Community extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "community_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private View view;

    @Embedded
    private CommunityImage communityImage;

    @Enumerated(EnumType.STRING)
    private CommunityType communityType = CommunityType.POST;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private final List<CommunityLike> communityLikes = new ArrayList<>();

    protected Community() {
    }

    private Community(User user, Title title, Content content, CommunityImage communityImage) {
        this.user = user;
        user.writeCommunity(this);
        this.title = title;
        this.content = content;
        this.view = new View();
        this.communityImage = communityImage;
    }

    public static Community create(User user, Title title, Content content, CommunityImage communityImage) {
        return new Community(user, title, content, communityImage);
    }

    public void update(Title title, Content content, CommunityImage communityImage) {
        this.title = title;
        this.content = content;
        this.communityImage = communityImage;
    }

    public void choiceType(String type) {
        if(user.isAdminRole()) {
            communityType = CommunityType.toEnum(type);
        }
    }

    public void validateHasSameUser(User user) {
        if(!this.user.equals(user)) {
            throw new DifferentUserException();
        }
    }

    public void addView() {
        view.addValue();
    }

    public boolean hasImage() {
        return communityImage.hasImage();
    }

    public void addCommunityLike(CommunityLike communityLike) {
        communityLikes.add(communityLike);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getContent() {
        return content.getValue();
    }

    public CommunityType getCommunityType() {
        return communityType;
    }

    public String getWriter() {
        return user.getName();
    }

    public String getWriterProfileImage() {
        return user.getProfileImageUrl();
    }

    public int getLikeCount() {
        return communityLikes.size();
    }

    public String getPath() {
        return communityImage.getPath();
    }

    public String getUrl() {
        return communityImage.getUrl();
    }
}
