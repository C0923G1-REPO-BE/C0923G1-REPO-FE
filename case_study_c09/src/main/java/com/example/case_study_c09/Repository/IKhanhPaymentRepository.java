package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKhanhPaymentRepository extends JpaRepository<Payment, Integer> {
}
