package com.hub.customerinfo.service;

import com.hub.customerinfo.model.dto.CustomerRequestDto;
import com.hub.customerinfo.model.dto.CustomerResponseDto;
import com.hub.customerinfo.model.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto findCustomerById(Long id);
    List<CustomerResponseDto> findAll();
    CustomerResponseDto updateCustomerById(CustomerRequestDto customerRequestDto);
}
