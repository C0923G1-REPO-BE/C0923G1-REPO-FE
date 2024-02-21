package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Category;
import com.example.case_study_c09.Repository.IGiauCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiauCategoryService implements IGiauCategoryService {
    @Autowired
    private IGiauCategoryRepository giauCategoryRepository;
    @Override
    public List<Category> findAll() {
        return giauCategoryRepository.findAll();
    }
}
