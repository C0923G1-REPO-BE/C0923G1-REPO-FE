package com.example.case_study_c09.DTO;

import com.example.case_study_c09.Model.Author;
import com.example.case_study_c09.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Validator {
    private Integer id;
    private String nameBook;
    private Double price;
    private Integer quantity;
    private LocalDate date = LocalDate.now();
    private String image;
    private String describeBook;
    private Author author;
    private Category category;
    private boolean isDelete;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
