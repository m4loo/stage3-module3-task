package com.mjc.school.repository.utils;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.*;

@Component
public class DataBaseLoader {
    private  DataBaseLoader() {

    }

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    private static final String AUTHORS_FILE_NAME = "authors";
    private static final String CONTENT_FILE_NAME = "content";
    private static final String NEWS_FILE_NAME = "news";
    private static final String TAGS_FILE_NAME = "tags";

    public void loadDataBase() {
        writeNews();
    }

    private void writeNews() {

        List<String> authors = Utils.readResourceFile(AUTHORS_FILE_NAME);
        List<String> tags = Utils.readResourceFile(TAGS_FILE_NAME);
        Set<Integer> prevs = new HashSet<>();
        List<TagModel> tagModels = new LinkedList<>();
        List<AuthorModel> authorModels = new LinkedList<>();

        Random random = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = random.nextInt(tags.size());
            if (prevs.contains(index)) {
                i--;
            } else {
                prevs.add(index);
                TagModel tagModel = new TagModel();
                AuthorModel authorModel = new AuthorModel();
                tagModel.setName(tags.get(index));
                authorModel.setName(authors.get(index));
                tagModels.add(tagModel);
                authorModels.add(authorModel);
            }
        }

        List<String> content = Utils.readResourceFile(CONTENT_FILE_NAME);
        List<String> titles = Utils.readResourceFile(NEWS_FILE_NAME);
        for (long i = 1; i <= 20; i++) {
            NewsModel newsModel = new NewsModel();
            for (int j = 0; j < random.nextInt(2, 6); j++) {
                newsModel.getTags().add(tagModels.get(random.nextInt(20)));
            }
            newsModel.setTitle(titles.get(random.nextInt(titles.size())));
            newsModel.setContent(content.get(random.nextInt(content.size())));
            newsModel.setAuthor(authorModels.get(random.nextInt(authorModels.size())));
            newsModel.setCreateDate(Utils.getRandomDate());
            newsModel.setLastUpdateDate(newsModel.getCreateDate());

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newsModel);
            entityManager.getTransaction().commit();
        }
    }
}
