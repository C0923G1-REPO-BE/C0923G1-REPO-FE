package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGiauCustomerService {
    Page<Customer> getList(Pageable pageable);
}
