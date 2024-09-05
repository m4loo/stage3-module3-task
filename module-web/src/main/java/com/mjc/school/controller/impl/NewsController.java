package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;
    private final BaseService<TagDtoRequest, TagDtoResponse, Long> tagService;

    private final BaseService<TagDtoRequest, TagDtoResponse, Long> authorService;

    @Autowired
    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService,
                          BaseService<TagDtoRequest, TagDtoResponse, Long> tagService, BaseService<TagDtoRequest, TagDtoResponse, Long> authorService) {
        this.newsService = newsService;
        this.tagService = tagService;
        this.authorService = authorService;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsService.readAll();
    }

    @Override
    public NewsDtoResponse readById(@CommandParam(name = "id") Long id) {
        return newsService.readById(id);
    }

    @Override
    public NewsDtoResponse create(@CommandBody NewsDtoRequest createRequest) {
        return newsService.create(createRequest);
    }

    @Override
    public NewsDtoResponse update(@CommandBody NewsDtoRequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @Override
    public boolean deleteById(@CommandParam(name = "id") Long id) {
        return newsService.deleteById(id);
    }

    public Set<NewsDtoResponse> getNewsByParam(List<Long> tagsId,
                                               List<String> tagName,
                                               String authorName,
                                               String title,
                                               String content) {

        List<Predicate<NewsDtoResponse>> predicateList = new ArrayList<>();
        if(tagsId != null && tagsId.size() !=0){
            predicateList.add(
                    newsDtoResponse -> new HashSet<>(newsDtoResponse.getTagsId().stream().toList()).containsAll(tagsId));
        }
        if(tagName != null && tagName.size()!=0){
            predicateList.add(newsDtoResponse -> new HashSet<>(newsDtoResponse.getTagsId().stream().map(tagService::readById)
                    .map(TagDtoResponse::getName).toList()).containsAll(tagName));
        }
        if(!authorName.isEmpty()){
            predicateList.add(newsDtoResponse -> authorService.readById(newsDtoResponse.getAuthorId()).getName().equalsIgnoreCase(authorName) );
        }
        if(!title.isEmpty()){
            predicateList.add(newsDtoResponse -> newsDtoResponse.getTitle().equalsIgnoreCase(title));
        }
        if(!content.isEmpty()){
            predicateList.add(newsDtoResponse -> newsDtoResponse.getContent().equalsIgnoreCase(content));
        }

        return newsService.readAll().stream().filter(predicateList.stream()
                .reduce(x -> true, Predicate::and)).collect(Collectors.toSet());
    }
}
