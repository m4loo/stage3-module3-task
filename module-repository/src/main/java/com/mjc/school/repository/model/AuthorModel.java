package com.mjc.school.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORS")
public class AuthorModel implements BaseEntity<Long> {

    @Id
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "AUTHOR_NAME")
    private String name;

    @Column(name = "AUTHOR_CREATE_DATE", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "AUTHOR_UPDATE_DATE")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "authorModel", fetch = FetchType.LAZY)
    private List<NewsModel> newsModelList = new ArrayList<>();
}