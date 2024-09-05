//package com.mjc.school.service;
//
//
//import com.mjc.school.repository.impl.AuthorRepository;
//import com.mjc.school.repository.impl.NewsRepository;
//import com.mjc.school.repository.model.impl.AuthorModel;
//import com.mjc.school.repository.model.impl.NewsModel;
//import com.mjc.school.service.dto.AuthorDtoResponse;
//import com.mjc.school.service.dto.NewsDtoRequest;
//import com.mjc.school.service.dto.NewsDtoResponse;
//import com.mjc.school.service.exception.InvalidDataException;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.impl.NewsService;
//import com.mjc.school.service.mapper.AuthorMapper;
//import com.mjc.school.service.mapper.NewsMapper;
//import com.mjc.school.service.mapper.TagMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class NewsServiceTest {
//
//    private final Long EXPECTED_ID = 3L;
//    private final String EXPECTED_TITLE = "Test Title";
//    private final String EXPECTED_CONTENT = "TEST CONTENT";
//
//    private final Long EXPECTED_AUTHOR_ID = 1L;
//    @Mock
//    private NewsRepository newsRepository;
//
//    @Mock
//    private AuthorRepository authorRepository;
//    @InjectMocks
//    private NewsService newsService;
//    private NewsDtoRequest newsDtoRequest;
//
//
//    @BeforeEach
//    public void init() {
//        newsDtoRequest = new NewsDtoRequest(EXPECTED_ID, EXPECTED_TITLE,
//                EXPECTED_CONTENT, 1L, List.of(2L, 3L, 4L));
//    }
//
//    @Test
//    void readAllTest() {
//        int expectedSize = 2;
//        NewsDtoRequest newsDtoRequest1 = new NewsDtoRequest(2L, "Test title2", "Test content2",
//                3L, List.of(1L, 4L));
//        NewsModel newsModel = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
//        NewsModel newsModel1 = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest1);
//        when(newsRepository.readAll()).thenReturn(List.of(newsModel, newsModel1));
//        List<NewsDtoResponse> newsDtoResponses = newsService.readAll();
//        assertNotNull(newsDtoResponses);
//        assertEquals(2, newsDtoResponses.size());
//    }
//
////    @Test
////    void updateTest() {
////        newsDtoRequest.setContent("Updated Content");
////        newsDtoRequest.setTitle("Updated Title");
////        NewsModel newsModel1 = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
////        given(newsRepository.update(newsModel1)).willReturn(newsModel1);
////        given(newsRepository.existById(any())).willReturn(true);
////        NewsDtoResponse newsDtoResponse = newsService.update(newsDtoRequest);
////        System.out.println(newsDtoResponse);
////    }
////
////    void createTest() {
////        NewsModel newsModel1 = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
////        AuthorModel authorModel = new AuthorModel(1l, "Some Author",
////                LocalDateTime.now(), LocalDateTime.now());
////        newsModel1.setAuthor(authorModel);
////        given(newsRepository.create(any(NewsModel.class))).willReturn(newsModel1);
////        when(authorRepository.existById(any())).thenReturn(true);
//////        when(authorRepository.readById(any())).thenReturn(Optional.of(authorModel));
////        NewsDtoResponse newsDtoResponse = newsService.create(newsDtoRequest);
////
////    }
//
////    @Test
////    void readByIdTest() {
////        NewsModel newsModel = NewsMapper.INSTANCE.newsFromDtoRequest(newsDtoRequest);
////        given(newsRepository.readById(EXPECTED_ID)).willReturn(Optional.of(newsModel));
////        NewsDtoResponse actual = newsService.readById(EXPECTED_ID);
////        assertNotNull(actual);
////        assertEquals(newsModel.getId(), actual.getId());
////        assertEquals(newsModel.getContent(), actual.getContent());
////        assertEquals(newsModel.getTitle(), actual.getTitle());
////    }
////
////    @Test
////     void deleteTest(){
////        given(newsRepository.deleteById(EXPECTED_ID)).willReturn(true);
////        given(newsRepository.existById(EXPECTED_ID)).willReturn(true);
////        assertTrue(newsService.deleteById(3L));
////     }
//
//     @Test()
//    void notValidTest(){
//        Long notValidId = 0L;
//         assertThrows(NotFoundException.class, ()-> newsService.deleteById(0L));
//     }
//
//}
