package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateId;
import com.mjc.school.service.annotation.ValidateParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService implements BaseService<TagDtoRequest, TagDtoResponse,Long> {

    private final BaseRepository<TagModel, Long> tagRepository;

    @Autowired
    public TagService(BaseRepository<TagModel, Long> tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<TagDtoResponse> readAll() {
        return tagRepository.readAll().stream().map(TagMapper.INSTANCE::tagToDtoResponse).toList();
    }

    @Override
    @ValidateId
    public TagDtoResponse readById(Long id) {
        Optional<TagModel> tagModel = tagRepository.readById(id);
        if(tagModel.isPresent()){
            return TagMapper.INSTANCE.tagToDtoResponse(tagModel.get());
        }
        throw new NotFoundException(String.format(ErrorCode.NOT_FOUND_DATA.getMessage()));
    }

    @Override
    @ValidateParam
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return TagMapper.INSTANCE.tagToDtoResponse(
                tagRepository.create(TagMapper.INSTANCE.tagFromDtoRequest(createRequest)));
    }

    @Override
    @ValidateParam
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        if(tagRepository.existById(updateRequest.getId())){
            TagModel tagModel = tagRepository.update(TagMapper.INSTANCE.tagFromDtoRequest(updateRequest));
            return TagMapper.INSTANCE.tagToDtoResponse(tagModel);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.TAG, updateRequest.getId()));
    }

    @Override
    @ValidateId
    public boolean deleteById(Long id) {
        if(tagRepository.existById(id)){
            return tagRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.TAG, id));
    }
}
