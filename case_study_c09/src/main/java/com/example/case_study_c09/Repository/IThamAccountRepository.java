package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IThamAccountRepository extends JpaRepository<Account,Integer> {

    Account findByUsername(String email);
}
