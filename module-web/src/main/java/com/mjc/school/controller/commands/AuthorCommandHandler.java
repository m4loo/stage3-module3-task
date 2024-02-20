package com.mjc.school.controller.commands;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.implementation.AuthorController;
import com.mjc.school.service.annotation.ValidateDto;
import com.mjc.school.service.aspects.ValidationAspect;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTORespond;
import com.mjc.school.service.exceptions.ExceptionService;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class AuthorCommandHandler implements BaseCommandHandler<AuthorController, AuthorDTORespond> {

    AuthorDTORequest authorDTORequest;

    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public String handleCommand(AuthorController controller, String buttonName) {
        boolean isValid = ValidationAspect.isValid();
        if (isValid) {
            CommandType commandType = Arrays.stream(CommandType.values())
                    .filter(type -> buttonName.contains(type.name()))
                    .findFirst()
                    .orElse(null);

            Method method = Stream.of(controller.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(CommandHandler.class))
                    .filter(m -> m.getAnnotation(CommandHandler.class).value().equals(commandType))
                    .findFirst()
                    .orElse(null);
            try {
                if (method != null) {
                    return switch (commandType) {
                        case READ_ALL -> toString((List<AuthorDTORespond>) method.invoke(controller));
                        case READ_BY_ID ->
                                toString((AuthorDTORespond) method.invoke(controller, authorDTORequest.getId())) + "\n";
                        case CREATE, UPDATE -> toString((AuthorDTORespond) method.invoke(controller, authorDTORequest));
                        case DELETE_BY_ID -> (boolean) method.invoke(controller, authorDTORequest.getId()) + "\n";
                    };
                } else throw new NotFoundException(ExceptionService.ERROR_COMMAND_NOT_FOUND.getErrorInfo());
            } catch (NotFoundException e) {
                System.out.println(e.getErrorMessage());
            }
        }
        ValidationAspect.setValid(true);
        return "";
    }

    @ValidateDto
    public void createRequest(String authorId, String name) {
        boolean isValid = ValidationAspect.isValid();
        if (isValid) {
            if (authorId == null)
                authorDTORequest = new AuthorDTORequest(null, name);
            else {
                Long id = Long.parseLong(authorId);
                authorDTORequest = new AuthorDTORequest(id, name);
            }
        }
    }

    @Override
    public String toString(AuthorDTORespond authorDTORespond) {
        if (authorDTORespond != null)
            return "AuthorDtoResponse[id=" + authorDTORespond.getId()
                    + ", name=" + authorDTORespond.getName()
                    + ", createDate=" + authorDTORespond.getCreateDate()
                    + ", lastUpdatedDate=" + authorDTORespond.getLastUpdateDate()
                    + "]";
        return "";
    }

    @Override
    public String toString(List<AuthorDTORespond> authorDTORespondList) {
        StringBuilder result = new StringBuilder();
        for (AuthorDTORespond authorDTORespond : authorDTORespondList)
            result.append(toString(authorDTORespond)).append("\n");
        return result.toString();
    }
}