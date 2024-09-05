package com.mjc.school.controller.command.impl.tag;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadAllTagsCommand implements Command {
    private final BaseController<TagDtoRequest, TagDtoResponse, Long> controller;


    public ReadAllTagsCommand(BaseController<TagDtoRequest, TagDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.readAll().stream().forEach(System.out::println);

    }
}
