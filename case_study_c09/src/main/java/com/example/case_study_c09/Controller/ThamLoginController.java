package com.example.case_study_c09.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ThamLoginController {
    @GetMapping("")
    public String show() {
        return "user-login";
    }

//    @GetMapping("/products")
//    public String product() {
//        return "products";
//    }

    @GetMapping("/user-login/contacts")
    public String contact() {
        return "contact-had-login";
    }

    @GetMapping("/user-login/faqs")
    public String faq() {
        return "faq-had-login";
    }

    @GetMapping("/abouts")
    public String about() {
        return "about-had-login";
    }
}
