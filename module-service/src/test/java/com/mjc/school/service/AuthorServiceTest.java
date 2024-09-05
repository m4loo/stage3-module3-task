//
//package com.mjc.school.service;
//
//
//import com.mjc.school.repository.impl.AuthorRepository;
//import com.mjc.school.repository.model.impl.AuthorModel;
//import com.mjc.school.service.dto.AuthorDtoRequest;
//import com.mjc.school.service.dto.AuthorDtoResponse;
//import com.mjc.school.service.exception.InvalidDataException;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.impl.AuthorService;
//import com.mjc.school.service.mapper.AuthorMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthorServiceTest {
//    private final Long EXPECTED_ID = 4l;
//    private final String EXPECTED_NAME = "TestAuthorName";
//    private final List<AuthorModel> authorList = new ArrayList<>();
//    private AuthorDtoRequest authorDtoRequest;
//    private AuthorModel authorModel;
//
//    @Mock
//    private AuthorRepository authorRepository;
//
//    @InjectMocks
//    private AuthorService authorService;
//
//    @BeforeEach
//    void init() {
//        authorDtoRequest = new AuthorDtoRequest(EXPECTED_ID, EXPECTED_NAME);
//        authorModel = AuthorMapper.INSTANCE.authorFromDtoRequest(authorDtoRequest);
//
//        authorList.add(new AuthorModel(1L, "Maik Herst", LocalDateTime.now(), LocalDateTime.now()));
//        authorList.add(new AuthorModel(2L, "Jodi Foster", LocalDateTime.now(), LocalDateTime.now()));
//        authorList.add(new AuthorModel(3L, "Henry Cavil", LocalDateTime.now(), LocalDateTime.now()));
//    }
//
//    @Test
//    void readAllTest() {
//        when(authorRepository.readAll()).thenReturn(authorList);
//        List<AuthorDtoResponse> authorDtoResponses = authorService.readAll();
//        assertNotNull(authorDtoResponses);
//        assertEquals(authorList.size(), authorDtoResponses.size());
//    }
//
//    @Test
//    void createTest() {
//        given(authorRepository.create(authorModel)).willReturn(authorModel);
//        AuthorDtoResponse actual = authorService.create(authorDtoRequest);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_NAME, actual.getName());
//    }
//
//    @Test
//    void readByIdTest() {
//        given(authorRepository.readById(EXPECTED_ID)).willReturn(Optional.of(authorModel));
//        AuthorDtoResponse actual = authorService.readById(EXPECTED_ID);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_NAME, actual.getName());
//    }
//
//    @Test
//    void updateTest() {
//        AuthorDtoRequest updatedRequest = authorDtoRequest;
//        authorDtoRequest.setName("updatedName");
//        AuthorModel updatedModel = AuthorMapper.INSTANCE.authorFromDtoRequest(updatedRequest);
//        given(authorRepository.update(updatedModel)).willReturn(authorModel);
//        given(authorRepository.existById(authorModel.getId())).willReturn(true);
//        AuthorDtoResponse actual = authorService.update(updatedRequest);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_NAME, actual.getName());
//    }
//
//    @Test
//    void deleteTest() {
//        given(authorRepository.deleteById(EXPECTED_ID)).willReturn(true);
//        given(authorRepository.existById(EXPECTED_ID)).willReturn(true);
//        assertTrue(authorService.deleteById(4L));
//    }
//
//    @Test()
//    void notValidIdTest() {
//        Long notValidId = 0L;
//        assertThrows(NotFoundException.class, () -> authorService.deleteById(notValidId));
//    }
//    @Test
//    void notValidNameTest() {
//        String notValidName = "Jak";
//        AuthorDtoRequest authorDtoRequest1 = new AuthorDtoRequest(null, notValidName);
//        when(authorRepository.create(argThat(argument -> argument.getName() == null ||
//                argument.getName().length() < 5)))
//                .thenThrow(InvalidDataException.class);
//        assertThrows(InvalidDataException.class, () ->
//                authorService.create(new AuthorDtoRequest(null, notValidName)));
//    }
//}
//
