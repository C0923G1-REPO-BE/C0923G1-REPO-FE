package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGiauAccountService {
    Page<Account> getList(Pageable pageable);
    Page<Account> getListLock(Pageable pageable);
    Account findById(int id);
    void save(Account account);
}
