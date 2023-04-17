package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.domain.user.domain.User;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_magazine")
public class Magazine {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "magazine_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    protected Magazine() {
    }

    private Magazine(User user, Title title, Content content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public static Magazine create(User user, Title title, Content content) {
        return new Magazine(user, title, content);
    }

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
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

    public User getUser() {
        return user;
    }

    public String getWriter() {
        return user.getName();
    }
}
