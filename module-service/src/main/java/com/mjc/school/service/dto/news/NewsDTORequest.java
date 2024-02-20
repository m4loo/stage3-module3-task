package com.mjc.school.service.dto.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewsDTORequest {
    private Long newsId;
    private String title;
    private String content;
    private Long authorId;
}
