package com.mjc.school.service.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateId;
import com.mjc.school.service.annotation.ValidateParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public AuthorService(BaseRepository<AuthorModel, Long> authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDtoResponse> readAll() {
        return authorRepository.readAll().stream().map(AuthorMapper.INSTANCE::authorToDtoResponse).toList();

    }

    @Override
    @ValidateId
    public AuthorDtoResponse readById(Long id) {
        Optional<AuthorModel> authorModel = authorRepository.readById(id);
        if (authorModel.isPresent()) {
            return AuthorMapper.INSTANCE.authorToDtoResponse(authorModel.get());
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }

    @Override
    @ValidateParam
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        return AuthorMapper.INSTANCE.authorToDtoResponse(
                authorRepository.create(AuthorMapper.INSTANCE.authorFromDtoRequest(createRequest)));
    }

    @Override
    @ValidateParam
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        if(authorRepository.existById(updateRequest.getId())){
            AuthorModel updateAuthor = authorRepository.update(AuthorMapper.INSTANCE.authorFromDtoRequest(updateRequest));
            return AuthorMapper.INSTANCE.authorToDtoResponse(updateAuthor);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, updateRequest.getId()));
    }

    @Override
    @ValidateId
    public boolean deleteById(Long id) {
        if(authorRepository.existById(id)){
            return authorRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }
}
