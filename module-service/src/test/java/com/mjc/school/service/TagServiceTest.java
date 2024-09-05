//package com.mjc.school.service;
//
//import com.mjc.school.repository.impl.TagRepository;
//import com.mjc.school.repository.model.impl.TagModel;
//import com.mjc.school.service.dto.TagDtoRequest;
//import com.mjc.school.service.dto.TagDtoResponse;
//import com.mjc.school.service.exception.InvalidDataException;
//import com.mjc.school.service.exception.NotFoundException;
//import com.mjc.school.service.impl.TagService;
//import com.mjc.school.service.mapper.TagMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.ArgumentMatchers.argThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//public class TagServiceTest {
//    private final Long EXPECTED_ID = 2l;
//    private final String EXPECTED_NAME = "TestTagName";
//    private TagDtoRequest tagDtoRequest;
//    private TagModel tagModel;
//
//    @Mock
//    private TagRepository tagRepository;
//
//    @InjectMocks
//    private TagService tagService;
//
//    @BeforeEach
//    void init() {
//        tagDtoRequest = new TagDtoRequest(EXPECTED_ID, EXPECTED_NAME);
//        tagModel = TagMapper.INSTANCE.tagFromDtoRequest(tagDtoRequest);
//    }
//
//    @Test
//    void readAllTest() {
//        Integer expectedSize = 2;
//        when(tagRepository.readAll()).thenReturn(List.of(
//                new TagModel(1L, "Sport"),
//                new TagModel(3L, "Beauty")
//        ));
//        List<TagDtoResponse> dtoResponseList = tagService.readAll();
//        assertNotNull(dtoResponseList);
//        assertEquals(expectedSize, dtoResponseList.size());
//    }
//
//    @Test
//    void createTest() {
//        given(tagRepository.create(tagModel)).willReturn(tagModel);
//        TagDtoResponse actual = tagService.create(tagDtoRequest);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_NAME, actual.getName());
//    }
//
//    @Test
//    void readByIdTest() {
//        given(tagRepository.readById(EXPECTED_ID)).willReturn(Optional.of(tagModel));
//        TagDtoResponse actual = tagService.readById(EXPECTED_ID);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(EXPECTED_NAME, actual.getName());
//    }
//
//    @Test
//    void updateTest() {
//        String updatedName ="updatedName";
//        TagDtoRequest updatedRequest = tagDtoRequest;
//        updatedRequest.setName(updatedName);
//        TagModel updatedModel = TagMapper.INSTANCE.tagFromDtoRequest(updatedRequest);
//        given(tagRepository.update(updatedModel)).willReturn(updatedModel);
//        given(tagRepository.existById(updatedRequest.getId())).willReturn(true);
//        TagDtoResponse actual = tagService.update(updatedRequest);
//        assertNotNull(actual);
//        assertEquals(EXPECTED_ID, actual.getId());
//        assertEquals(updatedName, actual.getName());
//    }
//
//    @Test
//    void deleteTest() {
//        given(tagRepository.deleteById(EXPECTED_ID)).willReturn(true);
//        given(tagRepository.existById(EXPECTED_ID)).willReturn(true);
//        assertTrue(tagService.deleteById(2L));
//    }
//
//    @Test()
//    void notValidIdTest() {
//        Long notValidId = 0L;
//        assertThrows(NotFoundException.class, () -> tagService.deleteById(notValidId));
//    }
//
//    @Test
//    void notValidNameTest() {
//        String notValidName = "Hot";
//        when(tagRepository.create(argThat(argument -> argument.getName() == null ||
//                argument.getName().length() < 5)))
//                .thenThrow(InvalidDataException.class);
//        assertThrows(InvalidDataException.class, () ->
//                tagService.create(new TagDtoRequest(null, notValidName)));
//    }
//}
