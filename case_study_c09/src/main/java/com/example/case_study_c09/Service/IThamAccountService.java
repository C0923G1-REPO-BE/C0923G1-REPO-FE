package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Account;

public interface IThamAccountService {
    Account findByUsername(String email);

    Account findById(Integer id);
}
