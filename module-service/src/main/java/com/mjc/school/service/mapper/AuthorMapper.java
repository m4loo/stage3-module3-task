package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorMapper implements BaseMapper<AuthorDTOResponse, AuthorModel, AuthorDTORequest> {

    ModelMapper modelMapper;

    public AuthorMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public AuthorDTOResponse convertModelToDTO(AuthorModel authorModel) {
        return modelMapper.map(authorModel, AuthorDTOResponse.class);
    }

    @Override
    public AuthorModel convertDTOtoModel(AuthorDTORequest authorDTORequest) {
        return modelMapper.map(authorDTORequest, AuthorModel.class);
    }

    @Override
    public List<AuthorDTOResponse> convertMoledListToDTOList(List<AuthorModel> authorModelList) {
        List<AuthorDTOResponse> authorDTORespondList = new ArrayList<>();
        for (AuthorModel authorModel : authorModelList)
            authorDTORespondList.add(convertModelToDTO(authorModel));
        return authorDTORespondList;
    }
}
