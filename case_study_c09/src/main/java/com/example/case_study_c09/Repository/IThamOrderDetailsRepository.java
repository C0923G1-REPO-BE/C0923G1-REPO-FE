package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IThamOrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
    Set<OrderDetails> findByOrders(Order order);
}
