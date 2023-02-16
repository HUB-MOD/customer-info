package com.hub.customerinfo.model.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDto {

    private Long documentNumber;
    private String firstName;
    private String lastName;
    private String documentType;
    private LocalDateTime responseDate;

}
