package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    private final String READ_ALL = "SELECT n FROM NewsModel n";
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    @Override
    public List<NewsModel> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(READ_ALL, NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        NewsModel updatedNews = entityManager.getReference(NewsModel.class, entity.getId());
        updatedNews.setTitle(entity.getTitle());
        updatedNews.setContent(entity.getContent());
        updatedNews.setAuthor(entity.getAuthor());
        updatedNews.setTags(entity.getTags());
        entityManager.getTransaction().commit();
        return updatedNews;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(readById(id).isPresent()){
            entityManager.getTransaction().begin();
            NewsModel news = entityManager.find(NewsModel.class, id);
            entityManager.remove(news);
            entityManager.getTransaction().commit();
            return !existById(id);
        }
        return false;
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
