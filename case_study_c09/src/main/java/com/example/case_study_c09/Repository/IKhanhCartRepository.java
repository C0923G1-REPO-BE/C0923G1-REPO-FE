package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKhanhCartRepository extends JpaRepository<Order, Integer> {

}
