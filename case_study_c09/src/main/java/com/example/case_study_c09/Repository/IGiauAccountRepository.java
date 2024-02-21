package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGiauAccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select * from account where is_delete = false", nativeQuery = true)
    Page<Account> findAccount(Pageable pageable);
    @Query(value = "select * from account where is_delete = true", nativeQuery = true)
    Page<Account> findAccountLock(Pageable pageable);
}
