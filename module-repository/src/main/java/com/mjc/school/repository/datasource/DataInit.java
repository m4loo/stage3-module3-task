package com.mjc.school.repository.datasource;

import com.mjc.school.repository.constants.Constants;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Configuration
public class DataInit {

    private List<AuthorModel> authorModelList;
    private List<TagModel> tagModelList;
    private static final Randomizer randomizer = new Randomizer();
    public static final Random random = new Random();

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    private boolean isDataBaseEmpty() {
        Query query = entityManager.createQuery("SELECT COUNT(a) FROM AuthorModel a");
        Long authorCount = (Long) query.getSingleResult();
        return authorCount == 0;
    }

    @Bean
    @Transactional
    public void initData() {
        if (isDataBaseEmpty()) {
            initAuthors();
            initTags();
            initNews();
        }
    }

    @Bean
    @SneakyThrows
    @Transactional
    void initAuthors() {
        authorModelList = new ArrayList<>();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.AUTHOR_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        List<String> lines = bufferedReader.lines().toList();

        for (String authorName : lines) {
            LocalDateTime createDate = randomizer.getRandomCreatedDate();
            LocalDateTime lastUpdateDate = randomizer.getRandomLastUpdatedDate(createDate);

            AuthorModel authorModel = new AuthorModel();
            authorModel.setName(authorName);
            authorModel.setCreateDate(createDate);
            authorModel.setLastUpdateDate(lastUpdateDate);
            authorModelList.add(authorModel);
            entityManager
                    .persist(authorModel);
        }
    }

    @Bean
    @Transactional
    void initTags() {
        tagModelList = new ArrayList<>();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.TAG_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        List<String> lines = bufferedReader.lines().toList();

        for (String tagName: lines) {
            TagModel tagModel = new TagModel();
            tagModel.setName(tagName);
            tagModelList.add(tagModel);

            entityManager.persist(tagModel);
        }
    }

    @Bean
    @Transactional
    void initNews() {
        for (int i = 0; i < Constants.TOTAL_NUMBER_OF_NEWS; i++) {

            LocalDateTime createDate = randomizer.getRandomCreatedDate();
            LocalDateTime lastUpdateDate = randomizer.getRandomLastUpdatedDate(createDate);
            Long authorId = randomizer.getRandomAuthorId(authorModelList);

            NewsModel newsModel = new NewsModel();
            newsModel.setTitle(randomizer.getRandomTitle());
            newsModel.setContent(randomizer.getRandomContent());
            newsModel.setCreateDate(createDate);
            newsModel.setLastUpdateDate(lastUpdateDate);
            newsModel.setAuthorModel(entityManager.find(AuthorModel.class, authorId));

            int randomNumberOfTags = random.nextInt(4);
            for (int j = 1; j <= randomNumberOfTags; j++) {
                Long tagId = randomizer.getRandomTagId(tagModelList);
                newsModel.addTagModel(entityManager.find(TagModel.class, tagId));
            }

            entityManager.persist(newsModel);
        }
    }
}
