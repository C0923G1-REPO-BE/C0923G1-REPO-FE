package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.Customer;
import com.example.case_study_c09.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IKhanhOrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from orders", nativeQuery = true)
    List<Order> findCustomerByIdAccount(Integer idAccount);

    @Query(value = "select account.* from account where account.id = :id;", nativeQuery = true)
    Account findAccountById(@Param("id")Integer idAccount);

//    select * from account join customer on account.customer_id = customer.id where account.id = 1;
}
