package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Author;
import com.example.case_study_c09.Repository.IGiauAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiauAuthorService implements IGiauAuthorService{
    @Autowired
    private IGiauAuthorRepository iGiauAuthorRepository;
    @Override
    public List<Author> findAll() {
        return iGiauAuthorRepository.findAll();
    }
}
