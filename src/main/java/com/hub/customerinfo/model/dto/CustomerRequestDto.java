package com.hub.customerinfo.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDto {

    private Long documentNumber;
    private String firstName;
    private String lastName;
    private String documentType;

}
