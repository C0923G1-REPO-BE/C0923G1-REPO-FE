package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGiauCategoryRepository extends JpaRepository<Category,Integer> {
}
