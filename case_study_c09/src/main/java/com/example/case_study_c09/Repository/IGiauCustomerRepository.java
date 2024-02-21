package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGiauCustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select * from customer where is_delete = false", nativeQuery = true)
    Page<Customer> findAllCustomer(Pageable pageable);
}
