package com.mjc.school.service.dto.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTORespond {
    private Long newsId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private Long authorId;
    private List<Long> tagIdList;
}