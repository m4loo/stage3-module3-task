package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    private final DataSource dataSource;

    @Override
    public List<AuthorModel> readAll() {
        return dataSource.getAuthorModelList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return readAll()
                .stream()
                .filter(authorModel -> id.equals(authorModel.getId()))
                .findAny();
    }

    @Override
    public AuthorModel create(AuthorModel authorModel) {
        List<Integer> listIndexAuthors = dataSource.getListIndexAuthor();
        authorModel.setId(listIndexAuthors.size() + 1L);
        dataSource.getListIndexAuthor().add(listIndexAuthors.size());
        authorModel.setCreateDate(LocalDateTime.now());
        authorModel.setLastUpdateDate(LocalDateTime.now());
        readAll().add(authorModel);
        return authorModel;
    }

    @Override
    public AuthorModel update(AuthorModel authorModel) {
        authorModel.setLastUpdateDate(LocalDateTime.now());
        readAll().set(Math.toIntExact(authorModel.getId() - 1), authorModel);
        return authorModel;
    }

    @OnDelete
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
                .anyMatch(authorModel -> id.equals(authorModel.getId()));
    }
}