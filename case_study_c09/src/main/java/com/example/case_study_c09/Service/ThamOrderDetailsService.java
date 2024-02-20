package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Model.OrderDetails;
import com.example.case_study_c09.Repository.IThamOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ThamOrderDetailsService implements IThamOrderDetailsService{
    @Autowired
    public IThamOrderDetailsRepository orderDetailsRepository;


    @Override
    public Set<OrderDetails> findByOrders(Order order) {
        return orderDetailsRepository.findByOrders(order);
    }
}
