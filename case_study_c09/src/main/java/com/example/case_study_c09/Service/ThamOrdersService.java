package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Repository.IThamOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ThamOrdersService implements IThamOrdersService{
    @Autowired
    private IThamOrdersRepository ordersRepository;

    @Override
    public Set<Order> findByAccountId(Integer id) {
        return ordersRepository.findByAccountId(id);
    }

    @Override
    public Order findById(int idOrder) {
        return ordersRepository.findById(idOrder).get();
    }


    @Override
    public void cancelOrder(int idOrder) {
        ordersRepository.cancelOrder(idOrder);
    }

    @Override
    public Set<Order> findByIdCheckDel(int idOrder) {
        return ordersRepository.findByIdCheckDel(idOrder);
    }


}
