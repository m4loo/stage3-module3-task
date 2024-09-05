package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {
    private final String READ_ALL = "SELECT t FROM TagModel t";
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;


    @Override
    public List<TagModel> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(READ_ALL, TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(TagModel.class, id));
    }

    @Override
    public TagModel create(TagModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TagModel updatedTag = entityManager.getReference(TagModel.class, entity.getId());
        updatedTag.setName(entity.getName());
        entityManager.getTransaction().commit();
        return updatedTag;
    }

    @Override
    public boolean deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(readById(id).isPresent()){
            entityManager.getTransaction().begin();
            TagModel tag = entityManager.find(TagModel.class, id);
            entityManager.remove(tag);
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
