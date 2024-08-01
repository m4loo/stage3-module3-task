package com.mjc.school.repository.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NEWS")
public class NewsModel implements BaseEntity<Long> {

    @Id
    @Column(name = "NEWS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NEWS_TITLE")
    private String title;

    @Column(name = "NEWS_CONTENT")
    private String content;

    @Column(name = "NEWS_CREATE_DATE", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "NEWS_UPDATE_DATE")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorModel authorModel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "NEWS_TAGS",
            joinColumns = @JoinColumn(name = "NEWS_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private List<TagModel> tagModelList;

    public void setAuthor(AuthorModel authorModel) {
        this.authorModel = authorModel;
        authorModel.getNewsModelList().add(this);
    }

    public void addTagModel(TagModel tagModel) {
        this.tagModelList.add(tagModel);
        tagModel.getNewsModelList().add(this);
    }
}
