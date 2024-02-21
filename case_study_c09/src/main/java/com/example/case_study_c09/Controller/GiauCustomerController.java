package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Customer;
import com.example.case_study_c09.Service.IGiauCategoryService;
import com.example.case_study_c09.Service.IGiauCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class GiauCustomerController {
    @Autowired
    private IGiauCustomerService service;
    @GetMapping("")
    public String show(@PageableDefault(value = 4)Pageable pageable, Model model){
        Page<Customer> customers = service.getList(pageable);
        model.addAttribute("customer", customers);
        return "list-customer";
    }
}
