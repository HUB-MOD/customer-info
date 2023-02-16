package com.hub.customerinfo.service.impl;

import com.hub.customerinfo.model.dto.CustomerRequestDto;
import com.hub.customerinfo.model.dto.CustomerResponseDto;
import com.hub.customerinfo.model.entity.CustomerEntity;
import com.hub.customerinfo.model.mapper.CustomerMapper;
import com.hub.customerinfo.model.repository.CustomerRepository;
import com.hub.customerinfo.service.CustomerService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto findCustomerById(Long id){

        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow();
        return CustomerMapper.INSTANCE.toDto(customerEntity);
    }

    @Override
    public List<CustomerResponseDto> findAll() {

        List<CustomerEntity> customerEntityList = Optional.of(customerRepository.findAll()).orElseThrow();

        return customerEntityList.stream()
                .map(customerEntity -> CustomerMapper.INSTANCE.toDto(customerEntity))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public CustomerResponseDto updateCustomerById(CustomerRequestDto customerRequestDto) {

        CustomerEntity customerEntity = CustomerMapper.INSTANCE.toEntity(customerRequestDto);

        customerRepository.setCustomerById(customerEntity.getFirstName(),
                                           customerEntity.getLastName(),
                                           customerEntity.getDocumentType(),
                                           customerEntity.getDocumentNumber());

        return CustomerMapper.INSTANCE.toDto(customerRepository.findById(customerEntity.getDocumentNumber()).orElseThrow());

    }

}
