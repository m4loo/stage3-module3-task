package com.mjc.school.service.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class NewsDtoRequest {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private List<Long> tagsId;

    public NewsDtoRequest(Long id, String title, String content, Long authorId, List<Long> tagsId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.tagsId = tagsId;
    }

    public NewsDtoRequest() {
    }

    public Long getId() {
        return id;
    }

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<Long> tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDtoRequest that = (NewsDtoRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(authorId, that.authorId) && Objects.equals(tagsId, that.tagsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, authorId, tagsId);
    }

    @Override
    public String toString() {
        return "NewsDtoRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
