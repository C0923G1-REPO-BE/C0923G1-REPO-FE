package com.example.case_study_c09.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "customer_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private boolean isDelete;

    @OneToMany(mappedBy = "customer_type")
    private Set<Customer> customers;
}
