package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotations.CommandBody;
import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.annotations.CommandParam;
import com.mjc.school.controller.commands.CommandType;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTORespond;
import com.mjc.school.service.implementation.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController implements BaseController<AuthorDTORequest, AuthorDTORespond, Long> {

    private final AuthorService authorService;

    @CommandHandler(value = CommandType.READ_ALL)
    @Override
    public List<AuthorDTORespond> readAll() {
        return authorService.readAll();
    }

    @CommandHandler(value = CommandType.READ_BY_ID)
    @Override
    public AuthorDTORespond readById(@CommandParam Long id) {
        return authorService.readById(id);
    }

    @CommandHandler(value = CommandType.CREATE)
    @Override
    public AuthorDTORespond create(@CommandBody AuthorDTORequest createRequest) {
        return authorService.create(createRequest);
    }

    @CommandHandler(value = CommandType.UPDATE)
    @Override
    public AuthorDTORespond update(@CommandBody AuthorDTORequest updateRequest) {
        return authorService.update(updateRequest);
    }

    @CommandHandler(value = CommandType.DELETE_BY_ID)
    @Override
    public boolean deleteById(@CommandParam Long id) {
        return authorService.deleteById(id);
    }
}