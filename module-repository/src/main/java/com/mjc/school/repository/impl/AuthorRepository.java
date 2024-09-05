package com.mjc.school.repository.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDeleteCascade;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository implements BaseRepository<AuthorModel, Long> {
    private final String READ_ALL = "SELECT a FROM AuthorModel a";
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public List<AuthorModel> readAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(READ_ALL, AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return Optional.ofNullable(entityManager.find(AuthorModel.class, id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        AuthorModel updatedAuthor = entityManager.getReference(AuthorModel.class, entity.getId());
        updatedAuthor.setName(entity.getName());
        entityManager.getTransaction().commit();
        return updatedAuthor;
    }


    @OnDeleteCascade
    @Override
    public boolean deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(readById(id).isPresent()){
            entityManager.getTransaction().begin();
            AuthorModel author = entityManager.find(AuthorModel.class, id);
            entityManager.remove(author);
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
