package com.mjc.school.repository.aspect;

import com.mjc.school.repository.datasource.DataSource;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class OnDeleteAspect {

    private final DataSource dataSource;

    @Before("@annotation(com.mjc.school.repository.annotation.OnDelete) && args(id)")
    public void deleteRelatedNews(Long id) {
        dataSource.getNewsModelList()
                .stream()
                .filter(newsModel -> id.equals(newsModel.getAuthorId()))
                .forEach(newsModel -> newsModel.setAuthorId(null));
        dataSource.getNewsModelList()
                .removeIf(newsModel -> newsModel.getAuthorId() == null);
    }
}
