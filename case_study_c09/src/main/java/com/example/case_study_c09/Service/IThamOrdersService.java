package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.Order;

import java.util.Set;

public interface IThamOrdersService {
    Set<Order> findByAccountId(Integer id);

    Order findById(int idOrder);


    void cancelOrder(int idOrder);

    Set<Order> findByIdCheckDel(int idOrder);
}
