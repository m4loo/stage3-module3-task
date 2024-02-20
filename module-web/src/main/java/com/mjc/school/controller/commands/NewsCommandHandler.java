package com.mjc.school.controller.commands;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.implementation.NewsController;
import com.mjc.school.service.annotation.ValidateDto;
import com.mjc.school.service.aspects.ValidationAspect;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTORespond;
import com.mjc.school.service.exceptions.ExceptionService;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class NewsCommandHandler implements BaseCommandHandler<NewsController, NewsDTORespond> {

    NewsDTORequest newsDTORequest;

    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public String handleCommand(NewsController controller, String buttonName) {
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
                        case READ_ALL ->
                                toString((List<NewsDTORespond>) method.invoke(controller));
                        case READ_BY_ID ->
                                toString((NewsDTORespond) method.invoke(controller, newsDTORequest.getNewsId())) + "\n";
                        case CREATE, UPDATE ->
                                toString((NewsDTORespond) method.invoke(controller, newsDTORequest));
                        case DELETE_BY_ID ->
                                (boolean) method.invoke(controller, newsDTORequest.getNewsId()) + "\n";
                    };
                } else throw new NotFoundException(ExceptionService.ERROR_COMMAND_NOT_FOUND.getErrorInfo());
            } catch (NotFoundException e) {
                System.out.println(e.getErrorMessage() + "\n");
            }
        }
        ValidationAspect.setValid(true);
        return "";
    }

    @ValidateDto
    public void createRequest(String newsId, String title, String content, String authorId) {
        boolean isValid = ValidationAspect.isValid();
        if (isValid) {
            Long authorIdLong = (authorId == null) ? null : Long.parseLong(authorId);
            Long newsIdLong = (newsId == null) ? null : Long.parseLong(newsId);
            newsDTORequest = new NewsDTORequest(newsIdLong, title, content, authorIdLong);
        }
    }

    @Override
    public String toString(NewsDTORespond newsDTORespond) {
        if (newsDTORespond != null)
            return "NewsDtoResponse[id=" + newsDTORespond.getNewsId()
                    + ", title=" + newsDTORespond.getTitle()
                    + ", content=" + newsDTORespond.getContent()
                    + ", createDate=" + newsDTORespond.getCreatedDate()
                    + ", lastUpdatedDate=" + newsDTORespond.getLastUpdatedDate()
                    + ", authorId=" + newsDTORespond.getAuthorId()
                    + "]";
        return "";
    }

    @Override
    public String toString(List<NewsDTORespond> newsDTORespondList) {
        StringBuilder result = new StringBuilder();
        for (NewsDTORespond newsDTORespond : newsDTORespondList)
            result.append(toString(newsDTORespond)).append("\n");
        return result.toString();
    }
}