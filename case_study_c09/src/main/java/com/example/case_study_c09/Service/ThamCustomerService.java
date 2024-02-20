package com.example.case_study_c09.Service;

import com.example.case_study_c09.DTO.CustomerDTO;
import com.example.case_study_c09.Model.Customer;
import com.example.case_study_c09.Repository.IThamCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ThamCustomerService implements IThamCustomerService{
    @Autowired
    private IThamCustomerRepository customerRepository;


    @Override
    public Customer finByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @Override
    public Customer finById(int idCustomer) {
        return customerRepository.findCustomerById(idCustomer);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
