package com.mjc.school.controller.command.impl.author;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public class ReadAllAuthorsCommand implements Command {
    private final BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller;


    public ReadAllAuthorsCommand(BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.readAll().stream().forEach(System.out::println);
    }
}
