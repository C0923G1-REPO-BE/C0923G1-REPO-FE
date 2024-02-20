package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Model.OrderDetails;

import java.util.Set;

public interface IThamOrderDetailsService {

    Set<OrderDetails> findByOrders(Order order);
}
