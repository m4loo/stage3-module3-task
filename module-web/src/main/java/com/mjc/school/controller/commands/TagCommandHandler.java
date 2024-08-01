package com.mjc.school.controller.commands;

import com.mjc.school.controller.annotations.CommandHandler;
import com.mjc.school.controller.implementation.TagController;
import com.mjc.school.service.annotation.ValidateTagDto;
import com.mjc.school.service.aspects.ValidationAspect;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTORespond;
import com.mjc.school.service.exceptions.ExceptionService;
import com.mjc.school.service.exceptions.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class TagCommandHandler implements BaseCommandHandler<TagController, TagDTORespond> {

    TagDTORequest tagDTORequest;

    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public String handleCommand(TagController controller, String buttonName) {
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
                        case READ_ALL -> toString((List<TagDTORespond>) method.invoke(controller));
                        case READ_BY_ID ->
                                toString((TagDTORespond) method.invoke(controller, tagDTORequest.getId())) + "\n";
                        case CREATE, UPDATE -> toString((TagDTORespond) method.invoke(controller, tagDTORequest));
                        case DELETE_BY_ID -> (boolean) method.invoke(controller, tagDTORequest.getId()) + "\n";
                    };
                } else throw new NotFoundException(ExceptionService.ERROR_COMMAND_NOT_FOUND.getErrorInfo());
            } catch (NotFoundException e) {
                System.out.println(e.getErrorMessage());
            }
        }
        ValidationAspect.setValid(true);
        return "";
    }

    @ValidateTagDto
    public void createRequest(String tagId, String tagName) {
        boolean isValid = ValidationAspect.isValid();
        if (isValid) {
            if (tagId == null)
                tagDTORequest = new TagDTORequest(null, tagName);
            else {
                Long id = Long.parseLong(tagId);
                tagDTORequest = new TagDTORequest(id, tagName);
            }
        }
    }

    @Override
    public String toString(TagDTORespond tagDTORespond) {
        if (tagDTORespond != null)
            return "TagDtoResponse[id=" + tagDTORespond.getId()
                    + ", name=" + tagDTORespond.getName()
                    + "]";
        return "";
    }

    @Override
    public String toString(List<TagDTORespond> respondList) {
        StringBuilder result = new StringBuilder();
        for (TagDTORespond tagDTORespond : respondList)
            result.append(toString(tagDTORespond)).append("\n");
        return result.toString();
    }
}