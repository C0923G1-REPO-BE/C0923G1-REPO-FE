package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping("/cart")
public class KhanhCartController {

    @GetMapping("")
    public String showCartPage(@SessionAttribute("cart") Map<Book, Integer> cartMap, Model model) {
        model.addAttribute("cartMap", cartMap);
        return "cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteBookCart(@PathVariable("id")Integer id, Model model, @SessionAttribute("cart") Map<Book, Integer> cartMap){
        for (Book book: cartMap.keySet()){
            if(book.getId().equals(id)){
                cartMap.remove(book);
                break;
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/save")
    public String saveToOrder(@ModelAttribute("address")String address){
        if (address == null || address == ""){
            return "cart";
        } else {
            return "successfully-order";
        }
    }



}
