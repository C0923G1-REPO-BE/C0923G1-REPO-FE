package com.example.case_study_c09.Controller;

import org.springframework.stereotype.Controller;
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
