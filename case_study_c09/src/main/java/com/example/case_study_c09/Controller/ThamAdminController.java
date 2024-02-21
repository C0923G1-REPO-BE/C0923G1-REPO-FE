package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Model.OrderAPI;
import com.example.case_study_c09.Service.IThamOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class ThamAdminController {
    @Autowired
    private IThamOrdersService ordersService;
    @PostMapping("")
    public void updateStatus(@RequestBody OrderAPI[] orderStt){
        List<Order> orderList =  ordersService.findAllList();
        for(Order order1:orderList){
            for (OrderAPI orderAPI1:orderStt){
                if (order1.getId().equals(orderAPI1.getOrderId())){
//                   order = new Order(order1.getId(),order1.getCode(),order1.getConsigneeName(),order1.getAddress(),order1.getPhone(),order1.getEmail(),order1.getBookingDate())
                    order1.setStatus(orderAPI1.getOrderStatus());
                    ordersService.save(order1);
                }
            }
        }
    }


}
