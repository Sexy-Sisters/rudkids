package com.rudkids.core.community.domain;

import com.rudkids.core.common.domain.AbstractEntity;
import com.rudkids.core.communityLike.domain.CommunityLike;
import com.rudkids.core.user.domain.User;
import com.rudkids.core.user.exception.DifferentUserException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_community")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private ViewCount viewCount;

    @Embedded
    private CommunityImage communityImage;

    @Enumerated(EnumType.STRING)
    private CommunityType communityType = CommunityType.POST;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community")
    private final List<CommunityLike> communityLikes = new ArrayList<>();

    private Community(User user, Title title, Content content, CommunityImage communityImage) {
        this.user = user;
        user.writeCommunity(this);
        this.title = title;
        this.content = content;
        this.viewCount = new ViewCount();
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

    public void increaseViewCount() {
        viewCount.increase();
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

    public void deleteCommunityImage() {
        communityImage.deleteImage();
    }

    public boolean isImageDeleted() {
        return communityImage.isDeleted();
    }
}
