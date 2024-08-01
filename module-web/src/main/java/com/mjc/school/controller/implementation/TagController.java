package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTORespond;
import com.mjc.school.service.implementation.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDTORequest, TagDTORespond, Long> {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @CommandHandler(value = CommandType.READ_ALL)
    @Override
    public List<TagDTORespond> readAll() {
        return tagService.readAll();
    }

    @CommandHandler(value = CommandType.READ_BY_ID)
    @Override
    public TagDTORespond readById(@CommandParam Long id) {
        return tagService.readById(id);
    }

    @CommandHandler(value = CommandType.CREATE)
    @Override
    public TagDTORespond create(@CommandBody TagDTORequest createRequest) {
        return tagService.create(createRequest);
    }

    @CommandHandler(value = CommandType.UPDATE)
    @Override
    public TagDTORespond update(@CommandBody TagDTORequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @CommandHandler(value = CommandType.DELETE_BY_ID)
    @Override
    public boolean deleteById(@CommandParam Long id) {
        return tagService.deleteById(id);
    }
}
