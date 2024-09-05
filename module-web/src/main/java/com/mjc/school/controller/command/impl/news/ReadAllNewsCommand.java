package com.mjc.school.controller.command.impl.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.command.Command;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

public class ReadAllNewsCommand implements Command {
    private final BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller;

    public ReadAllNewsCommand(BaseController<NewsDtoRequest, NewsDtoResponse, Long> controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.readAll().stream().forEach(System.out::println);
    }
}
