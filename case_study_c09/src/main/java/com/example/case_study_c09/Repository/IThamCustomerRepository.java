package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository

public interface IThamCustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByEmail(String email);

    Customer findCustomerById(int idCustomer);

}
