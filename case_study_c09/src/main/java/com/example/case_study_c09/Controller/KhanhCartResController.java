package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Model.CartBook;
import com.example.case_study_c09.Service.IKhanhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cart")
public class KhanhCartResController {
    @Autowired
    private IKhanhBookService iKhanhBookService;

    @PostMapping("/save")
    public void saveCartToOrder(@RequestBody CartBook[] bookData) {

        System.out.println("co vao :))");
        List<CartBook> cartBookList = new ArrayList<>();
        String address = String.valueOf(bookData[bookData.length - 1].getValue());

        for (int i = 0; i < bookData.length - 1; i++) {
            cartBookList.add(bookData[i]);
        }

        iKhanhBookService.addOrder(cartBookList, address);

    }

    @PostMapping("/update-cart")
    public ResponseEntity<String> updateCart(@RequestBody CartBook[] bookData, @SessionAttribute("cart") Map<Book, Integer> cartMap) {

        for (CartBook cartBook : bookData) {
            for (Book book : cartMap.keySet()) {
                if (book.getId().equals(Integer.valueOf(cartBook.getIdBook()))) {
                    cartMap.put(book, Integer.valueOf(cartBook.getValue()));
                    break;
                }
            }
        }
        return ResponseEntity.ok("Cart updated successfully");
    }
}
