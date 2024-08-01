package com.mjc.school.service.validation;

import com.mjc.school.service.exceptions.ExceptionService;
import com.mjc.school.service.exceptions.InputExceptions;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validator {

    private static final int CHAR_MIN = 5;
    private static final int NEWS_TITLE_MAX = 30;
    private static final int NEWS_CONTENT_MAX = 255;
    private static final int AUTHOR_NAME_MAX = 15;
    private static final int TAG_NAME_MAX = 15;

    public void checkNewsId(String newsId) throws InputExceptions {
        checkFormat(newsId, ExceptionService.Constants.NEWS);
        checkId(Long.parseLong(newsId), ExceptionService.Constants.NEWS);
    }

    public void checkAuthorId(String authorId) throws InputExceptions {
        checkFormat(authorId, ExceptionService.Constants.AUTHOR);
        checkId(Long.parseLong(authorId), ExceptionService.Constants.AUTHOR);
    }

    public void checkTagId(String tagId) throws InputExceptions {
        checkFormat(tagId, ExceptionService.Constants.TAG);
        checkId(Long.parseLong(tagId), ExceptionService.Constants.TAG);
    }

    public void checkTitle(String title) throws InputExceptions {
        checkCharLength(title, ExceptionService.Constants.NEWS, ExceptionService.Constants.TITLE, NEWS_TITLE_MAX);
    }

    public void checkContent(String content) throws InputExceptions {
        checkCharLength(content, ExceptionService.Constants.NEWS, ExceptionService.Constants.CONTENT, NEWS_CONTENT_MAX);
    }

    public void checkAuthorName(String name) throws InputExceptions {
        checkCharLength(name, ExceptionService.Constants.AUTHOR, ExceptionService.Constants.NAME, AUTHOR_NAME_MAX);
    }

    public void checkTagName(String name) throws InputExceptions {
        checkCharLength(name, ExceptionService.Constants.TAG, ExceptionService.Constants.NAME, TAG_NAME_MAX);
    }

    public void checkFormat(String id, String entity) throws InputExceptions {
        if (!NumberUtils.isParsable(id))
            throw new InputExceptions(String.format(ExceptionService.ERROR_FORMAT.getErrorInfo(entity)));
    }

    public void checkId(Long id, String entity) throws InputExceptions {
        if (id == null || id <= 0)
            throw new InputExceptions(String.format(ExceptionService.ERROR_ID_LENGTH.getErrorInfo(entity, id)));
    }

    public void checkCharLength(String str, String entity, String object, int max) throws InputExceptions {
        if (str.length() < CHAR_MIN || str.length() > max)
            throw new InputExceptions(String.format(ExceptionService.ERROR_CHAR_LENGTH.getErrorInfo(str, entity, object, max)));
    }

    public void checkNewsDto(String title, String content, String authorId, List<String> tagIdList) throws InputExceptions {
        if (title != null) checkTitle(title);
        if (content != null) checkContent(content);
        if (authorId != null) checkAuthorId(authorId);
        for (String tagId: tagIdList)
            if (tagId != null)
                checkTagId(tagId);
    }

    public void checkAuthorDto(String authorId, String authorName) throws InputExceptions {
        if (authorId != null) checkAuthorId(authorId);
        if (authorName != null) checkAuthorName(authorName);
    }

    public void checkTagDto(String tagId, String tagName) throws InputExceptions {
        if (tagId != null) checkTagId(tagId);
        if (tagName != null) checkTagName(tagName);
    }
}