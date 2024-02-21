package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKhanhOrderDetailRepository extends JpaRepository<OrderDetails, Integer> {
}
