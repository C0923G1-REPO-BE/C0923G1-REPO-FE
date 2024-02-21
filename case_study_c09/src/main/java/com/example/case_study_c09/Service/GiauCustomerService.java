package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Customer;
import com.example.case_study_c09.Repository.IGiauCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GiauCustomerService implements IGiauCustomerService{
    @Autowired
    private IGiauCustomerRepository repository;
    @Override
    public Page<Customer> getList(Pageable pageable) {
        return repository.findAllCustomer(pageable);
    }
}
