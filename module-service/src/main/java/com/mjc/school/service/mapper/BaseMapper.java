package com.mjc.school.service.mapper;

import java.util.List;

public interface BaseMapper<D, M, R> {

    D convertModelToDTO(M model);

    M convertDTOtoModel(R dto);

    List<D> convertMoledListToDTOList(List<M> modelList);
}