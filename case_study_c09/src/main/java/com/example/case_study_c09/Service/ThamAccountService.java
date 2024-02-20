package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Repository.IThamAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThamAccountService implements IThamAccountService{
    @Autowired
    private IThamAccountRepository accountRepository;
    @Override
    public Account findByUsername(String email) {
        return accountRepository.findByUsername(email);
    }

    @Override
    public Account findById(Integer id) {
        return accountRepository.findById(id).get();
    }
}
