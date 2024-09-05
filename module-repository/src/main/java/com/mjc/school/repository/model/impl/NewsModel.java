package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Scope("prototype")
@Entity
@Table(name = "news")
@EntityListeners({AuditingEntityListener.class})
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorModel author;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "news_tag", joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagModel> tags;


    public NewsModel() {
    }

    public NewsModel(Long id,
                     String title,
                     String content,
                     LocalDateTime createDate,
                     LocalDateTime lastUpdateDate,
                     AuthorModel author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.author = author;
    }

    public NewsModel(Long id,
                     String title,
                     String content,
                     LocalDateTime createDate,
                     LocalDateTime lastUpdateDate,
                     AuthorModel author,
                     List<TagModel> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.author = author;
        this.tags = tags;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }


    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel authorModel) {
        this.author = authorModel;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsModel newsModel = (NewsModel) o;
        return Objects.equals(id, newsModel.id) && Objects.equals(title, newsModel.title) && Objects.equals(content, newsModel.content) && Objects.equals(createDate, newsModel.createDate) && Objects.equals(lastUpdateDate, newsModel.lastUpdateDate) && Objects.equals(author, newsModel.author) && Objects.equals(tags, newsModel.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createDate, lastUpdateDate, author, tags);
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", author=" + author +
                '}';
    }
}

