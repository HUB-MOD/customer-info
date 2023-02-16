package com.hub.customerinfo.model.repository;

import com.hub.customerinfo.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Modifying
    @Query("UPDATE CustomerEntity ce SET ce.firstName = ?1, ce.lastName = ?2, ce.documentType = ?3 WHERE ce.documentNumber = ?4")
    void setCustomerById(String firstName, String lastName, String documentType, Long documentNumber);

}
