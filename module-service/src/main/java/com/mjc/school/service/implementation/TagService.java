package com.mjc.school.service.implementation;

import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTORespond;
import com.mjc.school.service.exceptions.ExceptionService;
import com.mjc.school.service.exceptions.NotFoundException;
import com.mjc.school.service.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagService implements BaseService<TagDTORequest, TagDTORespond, Long> {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper = new TagMapper();

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDTORespond> readAll() {
        return tagMapper.convertMoledListToDTOList(tagRepository.readAll());
    }

    @Override
    public TagDTORespond readById(Long id) {
        try {
            return tagRepository
                    .readById(id)
                    .map(tagMapper::convertModelToDTO)
                    .orElseThrow(()
                            -> new NotFoundException(
                            ExceptionService.ERROR_NOT_EXIST.getErrorInfo(
                                    ExceptionService.Constants.TAG,
                                    id)
                    ));
        } catch (NotFoundException e) {
            System.out.println(e.getErrorMessage());
        }
        return null;
    }

    @Override
    public TagDTORespond create(TagDTORequest createRequest) {
        TagModel tagModel = tagMapper.convertDTOtoModel(createRequest);
        tagRepository.create(tagModel);
        return tagMapper.convertModelToDTO(tagModel);
    }

    @Override
    public TagDTORespond update(TagDTORequest updateRequest) {
        try {
            if (tagRepository.existById(updateRequest.getId())) {
                TagModel tagModel = tagMapper.convertDTOtoModel(updateRequest);
                tagRepository.update(tagModel);
                return tagMapper.convertModelToDTO(tagModel);
            } else
                throw new NotFoundException(ExceptionService.ERROR_NOT_EXIST.getErrorInfo(
                        ExceptionService.Constants.TAG,
                        updateRequest.getId()));
        } catch (NotFoundException e) {
            System.out.println(e.getErrorMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            if (tagRepository.existById(id))
                return tagRepository.deleteById(id);
            else throw new NotFoundException(ExceptionService.ERROR_NOT_EXIST.getErrorInfo(
                    ExceptionService.Constants.TAG,
                    id));
        } catch (NotFoundException e) {
            System.out.println(e.getErrorMessage());
        }
        return false;
    }
}
