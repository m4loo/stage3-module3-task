package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDeleteCascade;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Aspect
@Component
public class ServiceAspect {

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.AuthorService.*(..)) && args(id)")
    public void validateAuthorId(Long id) {
        Validator.validateAuthorId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.AuthorService.*(..))  " +
            "&& args(request)")
    public void validateAuthorDto(AuthorDtoRequest request) {
        Validator.validateAuthorDto(request);

    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.AuthorService.*(..)) && args(id)")
    public void validateNewsId(Long id) {
        Validator.validateNewsId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.NewsService.*(..))" +
            "&& args(request)")
    public void validateNewsDto(NewsDtoRequest request) {
        Validator.validateNewsDto(request);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateId)" +
            "&& execution(public * com.mjc.school.service.impl.TagService.*(..)) && args(id)")
    public void validateTagId(Long id) {
        Validator.validateTagId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateParam) " +
            "&& execution(public * com.mjc.school.service.impl.TagService.*(..))  " +
            "&& args(request)")
    public void validateTagDto(TagDtoRequest request) {
        Validator.validateTagDto(request);

    }
}
