package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class DataSource {
    private final List<AuthorModel> authorModelList;
    private final List<NewsModel> newsModelList;
    private final List<Integer> listIndexAuthor;
    private final List<Integer> listIndexNews;

    @Autowired
    public DataSource (DataInit dataInit) {
        authorModelList = dataInit.getAuthorModelList();
        newsModelList = dataInit.getNewsModelList();
        listIndexAuthor = dataInit.getListIndexAuthor();
        listIndexNews = dataInit.getListIndexNews();
    }
}
