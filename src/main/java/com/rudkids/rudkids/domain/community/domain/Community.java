package com.rudkids.rudkids.domain.community.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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

    @Enumerated(EnumType.STRING)
    private CommunityType communityType = CommunityType.POST;

    protected Community() {
    }

    private Community(User user, Title title, Content content) {
        this.user = user;
        user.writeCommunity(this);
        this.title = title;
        this.content = content;
    }

    public static Community create(User user, Title title, Content content) {
        return new Community(user, title, content);
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public void choiceType(String type) {
        if(user.isAdminRole()) {
            communityType = CommunityType.toEnum(type);
        }
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
}
