package com.example.case_study_c09.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class GiauController {
    @GetMapping("")
    public String show(){
        return "index";
    }
}
