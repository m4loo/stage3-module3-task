package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring",uses = {TagMapper.class, AuthorMapper.class})
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "author.id", source = "authorId")
    @Mapping(source = "tagsId", target = "tags", qualifiedByName = "tagModelFromTagId")
    NewsModel newsFromDtoRequest(NewsDtoRequest request);

    @Named("tagModelFromTagId")
    default List<TagModel> tagModelFormTagId(List<Long> tagIdList) {
        List<TagModel> tags = new ArrayList<>();
        if (tagIdList != null) {
            tagIdList.stream().forEach(
                    o -> {
                        TagModel tag = new TagModel();
                        tag.setId(o);
                        tags.add(tag);
                    }
            );
        }
        return tags;

    }
    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "tagsId", source = "tags", qualifiedByName = "tagModelToTagId")
    NewsDtoResponse newsToDtoResponse(NewsModel model);


    @Named("tagModelToTagId")
    default List<Long> tagModelToTagId(List<TagModel> tags){
        List<Long> tagsListId = new ArrayList<>();
        if(tags!= null){
            tags.stream().forEach(
                    o -> {
                       tagsListId.add(o.getId());
                    });
        }
        return tagsListId;
    }


}
