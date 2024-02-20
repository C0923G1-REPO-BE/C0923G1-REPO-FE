package com.example.case_study_c09.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String consigneeName;
    private String address;
    private String phone;
    private String email;
    private LocalDate bookingDate;
    private LocalDate deliveryDate;
    private String status;
    private Integer paymentCode;
    private boolean isDelete;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;

}
