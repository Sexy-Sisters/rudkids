package com.rudkids.core.communityLike.domain;

import com.rudkids.core.community.domain.Community;
import com.rudkids.core.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_community_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityLike {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "community_like_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private CommunityLike(Community community, User user) {
        this.community = community;
        community.addCommunityLike(this);
        this.user = user;
    }

    public static CommunityLike create(Community community, User user) {
        return new CommunityLike(community, user);
    }
}
