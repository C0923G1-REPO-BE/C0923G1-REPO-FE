package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Repository.IGiauAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GiauAccountService implements IGiauAccountService{
    @Autowired
    private IGiauAccountRepository repository;
    @Override
    public Page<Account> getList(Pageable pageable) {
        return repository.findAccount(pageable);
    }

    @Override
    public Page<Account> getListLock(Pageable pageable) {
        return repository.findAccountLock(pageable);
    }

    @Override
    public Account findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(Account account) {
        repository.save(account);
    }
}
