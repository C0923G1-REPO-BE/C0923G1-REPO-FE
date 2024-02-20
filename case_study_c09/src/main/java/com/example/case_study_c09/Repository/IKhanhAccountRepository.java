package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IKhanhAccountRepository extends JpaRepository<Account, Integer> {
}
