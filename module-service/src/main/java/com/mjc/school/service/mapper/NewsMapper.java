package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTORespond;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper implements BaseMapper<NewsDTORespond, NewsModel, NewsDTORequest>{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public NewsDTORespond convertModelToDTO(NewsModel newsModel) {
        NewsDTORespond newsDTORespond = new NewsDTORespond();
        newsDTORespond.setNewsId(newsModel.getId());
        newsDTORespond.setTitle(newsModel.getTitle());
        newsDTORespond.setContent(newsModel.getContent());
        newsDTORespond.setAuthorId(newsModel.getAuthorModel().getId());
        newsDTORespond.setTagIdList(entityManager.createQuery("SELECT id FROM TAGS", Long.class).getResultList());
        return newsDTORespond;
    }

    @Override
    public NewsModel convertDTOtoModel(NewsDTORequest newsDTORequests) {
        NewsModel newsModel = new NewsModel();
        newsModel.setId(newsDTORequests.getNewsId());
        newsModel.setTitle(newsDTORequests.getTitle());
        newsModel.setContent(newsDTORequests.getContent());
        newsModel.setAuthorModel(entityManager.find(AuthorModel.class, newsDTORequests.getAuthorId()));

        List<Long> tagIdList = newsDTORequests.getTagIdList();
        List<TagModel> tagModelList = entityManager
                .createQuery("SELECT t FROM TAGS t WHERE t.id IN :tagIdList", TagModel.class)
                .setParameter("tagIdList", tagIdList)
                .getResultList();

        newsModel.setTagModelList(tagModelList);
        return newsModel;
    }

    @Override
    public List<NewsDTORespond> convertMoledListToDTOList(List<NewsModel> newsModelList) {
        List<NewsDTORespond> newsDTORespondList = new ArrayList<>();
        for (NewsModel newsModel : newsModelList){
            NewsDTORespond newsDTORespond = convertModelToDTO(newsModel);
            newsDTORespondList.add(newsDTORespond);
        }
        return newsDTORespondList;
    }
}
