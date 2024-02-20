package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTORespond;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper implements BaseMapper<NewsDTORespond, NewsModel, NewsDTORequest>{

    @Override
    public NewsDTORespond convertModelToDTO(NewsModel newsModel) {
        NewsDTORespond newsDTORespond = new NewsDTORespond();
        newsDTORespond.setNewsId(newsModel.getId());
        newsDTORespond.setTitle(newsModel.getTitle());
        newsDTORespond.setContent(newsModel.getContent());
        newsDTORespond.setAuthorId(newsModel.getAuthorId());
        return newsDTORespond;
    }

    @Override
    public NewsModel convertDTOtoModel(NewsDTORequest newsDTORequests) {
        NewsModel newsModel = new NewsModel();
        newsModel.setId(newsDTORequests.getNewsId());
        newsModel.setTitle(newsDTORequests.getTitle());
        newsModel.setContent(newsDTORequests.getContent());
        newsModel.setAuthorId(newsDTORequests.getAuthorId());
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
