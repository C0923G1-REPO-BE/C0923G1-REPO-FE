package com.example.case_study_c09.Service;

import com.example.case_study_c09.DTO.CustomerDTO;
import com.example.case_study_c09.Model.Customer;
import org.springframework.stereotype.Service;


public interface IThamCustomerService {
    Customer finByEmail(String email);

    Customer finById(int idCustomer);

    void save(Customer customer);
}
