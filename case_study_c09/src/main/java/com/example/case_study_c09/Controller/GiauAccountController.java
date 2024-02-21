package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Account;
import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Service.IGiauAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class GiauAccountController {
    @Autowired
    private IGiauAccountService service;
    @GetMapping("")
    public String show(@PageableDefault(value = 9)Pageable pageable, Model model){
        Page<Account> accounts = service.getList(pageable);
        model.addAttribute("account",accounts);
        return "list-account";
    }
    @GetMapping("/lock")
    public String showLock(@PageableDefault(value = 9)Pageable pageable, Model model){
        Page<Account> accounts = service.getListLock(pageable);
        model.addAttribute("account",accounts);
        return "list-account-lock";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        Account account = service.findById(id);
        account.setDelete(true);
        service.save(account);
        return "redirect:/account";
    }
    @PostMapping("/unlock")
    public String unlock(@RequestParam("id") int id){
        Account account = service.findById(id);
        account.setDelete(false);
        service.save(account);
        return "redirect:/account/lock";
    }
}
