package com.example.case_study_c09.DTO;


import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.CustomerType;
import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO  {
    @PositiveOrZero
    private Integer id;

    private String code;
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 2, max = 50, message = "Tên trên 2 kí tự và không quá 50 kí tự ")
    private String name;
    @Pattern(regexp = "^0[0-9]{9}$", message = "Sđt bắt đầu từ số 0 và có 10 số")
    private String phone;
    private String email;
    private String gender;
    private boolean isDelete;


    private Set<Account> accounts;


    private CustomerType customer_type;


}


