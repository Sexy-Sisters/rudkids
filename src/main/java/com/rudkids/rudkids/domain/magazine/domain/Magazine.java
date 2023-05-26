package com.rudkids.rudkids.domain.magazine.domain;

import com.rudkids.rudkids.common.AbstractEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_magazine")
public class Magazine extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "magazine_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private Writer writer;

    protected Magazine() {
    }

    private Magazine(Title title, Content content, Writer writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static Magazine create(Title title, Content content, Writer writer) {
        return new Magazine(title, content, writer);
    }

    public void update(Title title, Content content, Writer writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
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

    public String getWriter() {
        return writer.getValue();
    }
}
