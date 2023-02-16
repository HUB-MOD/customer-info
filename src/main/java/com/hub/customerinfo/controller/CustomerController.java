package com.hub.customerinfo.controller;

import com.hub.customerinfo.model.dto.CustomerRequestDto;
import com.hub.customerinfo.model.dto.CustomerResponseDto;
import com.hub.customerinfo.model.entity.CustomerEntity;
import com.hub.customerinfo.service.CustomerService;
import com.hub.customerinfo.utils.doc.ApiDoc;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = ApiDoc.CUSTOMER_INFO, description = "API for get Customer Info")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/customer/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Operation(
            operationId = "Method GET 'getCustomerById' for get Customer Info",
            summary = "Get Customer Info by Id",
            description = "Show the Customer Info found by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiDoc.RESPONSE_OK, description = "Customer Info found successful"),
            @ApiResponse(responseCode = ApiDoc.RESPONSE_NOT_FOUND, description = "Customer not found") })
    @GetMapping("/{id}")
    ResponseEntity<?> getCustomerById(@Parameter(name = "Customer Id")
                                      @PathVariable(name = "id") Long idCustomer) {
        return ResponseEntity.ok(customerService.findCustomerById(idCustomer));
    }

    @Operation(
            operationId = "Method GET 'getAllCustomers' for get all Customers Info",
            summary = "Get All Customers Info ",
            description = "Show all Customers Info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiDoc.RESPONSE_OK , description = "All Customers Info found successful"),
            @ApiResponse(responseCode = ApiDoc.RESPONSE_NOT_FOUND, description = "No Data of Customers") })
    @GetMapping("/all")
    ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @Operation(
            operationId = "Method PUT 'updateCustomer' for update Customer Info",
            summary = "Update Customers Info ",
            description = "Update Customers Info by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ApiDoc.RESPONSE_OK , description = "Customer Info update successful"),
            @ApiResponse(responseCode = ApiDoc.RESPONSE_NOT_FOUND, description = "Customer not found") })
    @PostMapping(value = "/update")
    ResponseEntity<?> updateCustomer(@RequestBody CustomerRequestDto requestDto) {
        return ResponseEntity.ok(customerService.updateCustomerById(new CustomerRequestDto(requestDto.getDocumentNumber(),
                                                                                            requestDto.getFirstName(),
                                                                                            requestDto.getLastName(),
                                                                                            requestDto.getDocumentType())));
    }
}
