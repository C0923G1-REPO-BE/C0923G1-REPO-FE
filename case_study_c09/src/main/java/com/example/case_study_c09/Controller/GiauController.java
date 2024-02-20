package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/")
public class GiauController {
    @GetMapping("")
    public String show() {
        return "index";
    }

    @GetMapping("/product")
    public String product() {
        return "products";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
