package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.TagModel;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTORespond;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper implements BaseMapper<TagDTORespond, TagModel, TagDTORequest>{

    ModelMapper modelMapper;

    public TagMapper() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    @Override
    public TagDTORespond convertModelToDTO(TagModel tagModel) {
        return modelMapper.map(tagModel, TagDTORespond.class);
    }

    @Override
    public TagModel convertDTOtoModel(TagDTORequest tagDTORequest) {
        return modelMapper.map(tagDTORequest, TagModel.class);
    }

    @Override
    public List<TagDTORespond> convertMoledListToDTOList(List<TagModel> tagModelList) {
        List<TagDTORespond> tagDTORespondList = new ArrayList<>();
        for (TagModel tagModel : tagModelList)
            tagDTORespondList.add(convertModelToDTO(tagModel));
        return tagDTORespondList;
    }
}
