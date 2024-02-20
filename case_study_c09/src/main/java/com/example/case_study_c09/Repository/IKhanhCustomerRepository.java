package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IKhanhCustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select customer.* from customer join account on account.customer_id = customer.id where account.id = :id", nativeQuery = true)
    Customer findCustomerByIdAccount(@Param("id") Integer idAccount);
}
