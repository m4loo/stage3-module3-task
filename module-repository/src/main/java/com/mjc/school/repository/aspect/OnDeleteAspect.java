package com.mjc.school.repository.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@Aspect
@RequiredArgsConstructor
public class OnDeleteAspect {

    EntityManager entityManager;

    @Before("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(id)")
    public void deleteRelatedNews(Long id) {

        entityManager.createQuery("UPDATE NEWS n SET c.authorId = :newId WHERE c.authorId = :oldId")
                .setParameter( "newId", null )
                .setParameter( "oldId", id )
                .executeUpdate();
        entityManager.createQuery("DELETE FROM NEWS WHERE authorId = :authorId")
                .setParameter("authorId", null)
                .executeUpdate();
    }
}
