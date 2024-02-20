package com.mjc.school.repository.datasource;

import com.mjc.school.repository.constants.Constants;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataInit {

    private final List<Integer> listIndexAuthor = new ArrayList<>();
    private final List<Integer> listIndexNews = new ArrayList<>();
    private List<AuthorModel> authorModelList;
    private List<NewsModel> newsModelList;

    private static final Randomizer randomizer = new Randomizer();

    public DataInit() {
        initNews(initAuthors());
    }

    @SneakyThrows
    private List<AuthorModel> initAuthors() {
        authorModelList = new ArrayList<>();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.AUTHOR_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        List<String> lines = bufferedReader.lines().toList();
        for (int i = 0; i < lines.size(); i++) {
            listIndexAuthor.add(i);
            Long authorId = (long) (i + 1);
            String authorName = lines.get(i);
            LocalDateTime createDate = randomizer.getRandomCreatedDate();
            LocalDateTime lastUpdateDate = randomizer.getRandomLastUpdatedDate(createDate);
            authorModelList.add(
                    new AuthorModel(
                            authorId,
                            authorName,
                            createDate,
                            lastUpdateDate));
        }
        return authorModelList;
    }

    private void initNews(List<AuthorModel> authorModelList) {
        newsModelList = new ArrayList<>();
        for (int i = 0; i < Constants.TOTAL_NUMBER_OF_NEWS; i++) {
            listIndexNews.add(i);
            Long newsId = (long) (i + 1);
            String title = randomizer.getRandomTitle();
            String content = randomizer.getRandomContent();
            LocalDateTime createDate = randomizer.getRandomCreatedDate();
            LocalDateTime lastUpdateDate = randomizer.getRandomLastUpdatedDate(createDate);
            Long authorId = randomizer.getRandomAuthorId(authorModelList);
            newsModelList.add(
                    new NewsModel(
                            newsId,
                            title,
                            content,
                            createDate,
                            lastUpdateDate,
                            authorId));
        }
    }
}
