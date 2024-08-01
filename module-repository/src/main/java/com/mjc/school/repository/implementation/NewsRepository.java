package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    @Autowired
    public NewsRepository() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<NewsModel> readAll() {
        return entityManager
                .createQuery("SELECT n FROM NEWS n", NewsModel.class)
                .getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(
                entityManager
                        .find(NewsModel.class, id));
    }

    @Override
    @Transactional
    public NewsModel create(NewsModel newsModel) {
        entityManager
                .persist(newsModel);
        return entityManager
                .find(NewsModel.class, newsModel.getId());
    }

    @Override
    @Transactional
    public NewsModel update(NewsModel newsModel) {
        entityManager
                .merge(newsModel);
        return entityManager
                .find(NewsModel.class, newsModel.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return entityManager.createQuery("DELETE FROM NEWS n.id = :id")
                .setParameter("id", id)
                .executeUpdate() > 0;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager
                .find(NewsModel.class, id) != null;
    }
}
