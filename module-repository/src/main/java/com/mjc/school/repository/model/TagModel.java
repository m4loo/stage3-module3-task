package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TAGS")
public class TagModel implements BaseEntity<Long>{

    @Id
    @Column(name = "TAG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TAG_NAME")
    private String name;

    @ManyToMany(mappedBy = "tagModelList", fetch = FetchType.LAZY)
    List<NewsModel> newsModelList = new ArrayList<>();

    public void addNewsModel(NewsModel newsModel) {
        this.newsModelList.add(newsModel);
        newsModel.getTagModelList().add(this);
    }
}
