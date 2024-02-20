package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNewsModelList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return readAll()
                .stream()
                .filter(newsModel -> id.equals(newsModel.getId()))
                .findAny();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        List<Integer> listIndexNews = dataSource.getListIndexNews();
        newsModel.setId(listIndexNews.size() + 1L);
        dataSource.getListIndexNews().add(listIndexNews.size());
        newsModel.setCreateDate(LocalDateTime.now());
        newsModel.setLastUpdateDate(LocalDateTime.now());
        readAll().add(newsModel);
        return newsModel;
    }

    @Override
    public NewsModel update(NewsModel newsModel) {
        newsModel.setLastUpdateDate(LocalDateTime.now());
        readAll().set(Math.toIntExact(newsModel.getId() - 1), newsModel);
        return newsModel;
    }

    @Override
    public boolean deleteById(Long id) {
        return readById(id)
                .map(newsModel -> readAll().remove(newsModel))
                .orElse(false);
    }

    @Override
    public boolean existById(Long id) {
        return readAll()
                .stream()
                .anyMatch(newsModel -> id.equals(newsModel.getId()));
    }
}
