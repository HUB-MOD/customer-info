package com.hub.customerinfo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_document", unique = true)
    private Long documentNumber;
    @Column(name = "first_name")
    @NotBlank(message = "firstname is required")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "document_type")
    @NotNull(message = "document type not must be null")
    @NotBlank(message = "document type is required")
    private String documentType;

}
