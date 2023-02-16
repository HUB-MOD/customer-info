package com.hub.customerinfo.model.mapper;

import com.hub.customerinfo.model.dto.CustomerRequestDto;
import com.hub.customerinfo.model.dto.CustomerResponseDto;
import com.hub.customerinfo.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(imports = LocalDateTime.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "responseDate", expression = "java(LocalDateTime.now())")
    CustomerResponseDto toDto(CustomerEntity customerEntity);

    CustomerEntity toEntity(CustomerRequestDto customerRequestDto);
}
