package com.example.case_study_c09.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartBook {
    //Nếu value là số thì là số lượng của giỏ hàng còn là chữ thì là address, address nằm ở cuối mảng
    private String idBook;
    private String value;

}
