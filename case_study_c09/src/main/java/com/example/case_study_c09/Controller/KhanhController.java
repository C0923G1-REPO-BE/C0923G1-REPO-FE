package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Service.IKhanhBookService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("cart")
@RequestMapping("/products")
public class KhanhController {
    @ModelAttribute("cart")
    public Map<Book, Integer> initSession() {
        return new HashMap<>();
    }

    @Autowired
    private IKhanhBookService iKhanhBookService;

    @GetMapping("")
    public String showProducts(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
//        List<Book> bookList = iKhanhBookService.findAll();
        Pageable bookPageable = PageRequest.of(page, 8);
        Page<Book> bookPage = this.iKhanhBookService.findAllBook(bookPageable);
        model.addAttribute("bookList", bookPage);
        return "products";
    }

    @GetMapping("/detail/{id}")
    public String showDetailProduct(Model model, @PathVariable("id") Integer id) {
        Book book = iKhanhBookService.findBookById(id);
        model.addAttribute("book", book);
        return "product-detail";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") Integer id, @ModelAttribute("cart") Map<Book, Integer> cartMap, RedirectAttributes redirectAttributes) {
        Book bookById = iKhanhBookService.findBookById(id);
        Integer newQuantity = null;
        int count = 0;
        for (Book book : cartMap.keySet()) {
            if (book.getId().equals(id)) {
                if (cartMap.get(book) < book.getQuantity()) {
                    newQuantity = cartMap.get(book) + 1;
                    cartMap.put(book, newQuantity);
                    count++;
                    break;
                }
            }
        }

        if (count == 0 && bookById.getQuantity() > 0) {
            cartMap.put(bookById, 1);
        }
        for (Book book : cartMap.keySet()) {
            System.out.println(book + "sl " + cartMap.get(book));
        }
        redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
        return "redirect:/products";
    }

    @PostMapping("/search")
    public String search(@RequestParam("search")String search, Model model, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        Pageable bookPageable = PageRequest.of(page, 8);
        Page<Book> bookPage = this.iKhanhBookService.findAllBookByName(search, bookPageable);
        model.addAttribute("bookList", bookPage);
        return "products";
    }

    @PostMapping("/sort")
    public String sort(@RequestParam("sort")Integer sort, Model model, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        Pageable bookPageable = PageRequest.of(page, 8);
        Page<Book> bookPage = this.iKhanhBookService.findAllBookByCategory(sort, bookPageable);
        model.addAttribute("bookList", bookPage);
        return "products";
    }
}
