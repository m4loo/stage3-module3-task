package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTORespond;
import com.mjc.school.service.implementation.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NewsController implements BaseController<NewsDTORequest, NewsDTORespond, Long> {

    private final NewsService newsService;

    @CommandHandler(value = CommandType.READ_ALL)
    @Override
    public List<NewsDTORespond> readAll() {
        return newsService.readAll();
    }

    @CommandHandler(value = CommandType.READ_BY_ID)
    @Override
    public NewsDTORespond readById(@CommandParam Long id) {
        return newsService.readById(id);
    }

    @CommandHandler(value = CommandType.CREATE)
    @Override
    public NewsDTORespond create(@CommandBody NewsDTORequest createRequest) {
        return newsService.create(createRequest);
    }

    @CommandHandler(value = CommandType.UPDATE)
    @Override
    public NewsDTORespond update(@CommandBody NewsDTORequest updateRequest) {
        return newsService.update(updateRequest);
    }

    @CommandHandler(value = CommandType.DELETE_BY_ID)
    @Override
    public boolean deleteById(@CommandParam Long id) {
        return newsService.deleteById(id);
    }
}
