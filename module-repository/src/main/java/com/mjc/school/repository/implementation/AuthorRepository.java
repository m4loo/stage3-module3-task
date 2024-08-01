package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDelete;
import com.mjc.school.repository.model.AuthorModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {

    @Autowired
    public AuthorRepository() {
    }
//    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AuthorModel> readAll() {
        return entityManager
                .createQuery("SELECT a FROM AUTHORS a", AuthorModel.class)
                .getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(
                entityManager
                        .find(AuthorModel.class, id));
    }

    @Override
    @Transactional
    public AuthorModel create(AuthorModel authorModel) {
        entityManager
                .persist(authorModel);
        return entityManager
                .find(AuthorModel.class, authorModel.getId());
    }

    @Override
    @Transactional
    public AuthorModel update(AuthorModel authorModel) {
        entityManager
                .merge(authorModel);
        return entityManager
                .find(AuthorModel.class, authorModel.getId());
    }

    @OnDelete
    @Override
    @Transactional
    public boolean deleteById(Long id) {
        return entityManager
                .createQuery("DELETE FROM AUTHORS a WHERE a.id = :id")
                .setParameter("id", id)
                .executeUpdate() > 0;
    }

    @Override
    public boolean existById(Long id) {
        return entityManager
                .find(AuthorModel.class, id) != null;
    }
}