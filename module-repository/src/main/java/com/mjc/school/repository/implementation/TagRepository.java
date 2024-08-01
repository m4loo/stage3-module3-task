package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.TagModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel, Long> {

    @Autowired
    public TagRepository() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagModel> readAll() {
        return entityManager
                .createQuery("SELECT t FROM TAGS t", TagModel.class)
                .getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(
                entityManager
                        .find(TagModel.class, id));
    }

    @Override
    @Transactional
    public TagModel create(TagModel tagModel) {
        entityManager
                .persist(tagModel);
        return entityManager
                .find(TagModel.class, tagModel.getId());
    }

    @Override
    @Transactional
    public TagModel update(TagModel tagModel) {
        entityManager.merge(tagModel);
        return entityManager
                .find(TagModel.class, tagModel.getId());
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return entityManager
                .createQuery("DELETE FROM TAGS t WHERE t.id = :id")
                .setParameter("id", id)
                .executeUpdate() > 0;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager
                .find(TagModel.class, id) != null;
    }
}
