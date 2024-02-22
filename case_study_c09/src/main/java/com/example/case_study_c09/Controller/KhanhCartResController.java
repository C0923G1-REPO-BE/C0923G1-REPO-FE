package com.example.case_study_c09.Controller;

import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Model.CartBook;
import com.example.case_study_c09.Service.IKhanhBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cart")
public class KhanhCartResController {
    @Autowired
    private IKhanhBookService iKhanhBookService;

    @PostMapping("/save")
    public void saveCartToOrder(@RequestBody CartBook[] bookData, @SessionAttribute("cart") Map<Book, Integer> cartMap) {

        System.out.println("co vao :))");
        List<CartBook> cartBookList = new ArrayList<>();
        String address = String.valueOf(bookData[bookData.length - 1].getValue());

        for (int i = 0; i < bookData.length - 1; i++) {
            cartBookList.add(bookData[i]);
        }

        //Neu thanh cong, xoa gio hang
        Boolean permitted = true;
        List<Book> books = iKhanhBookService.findAllList();
        for (Book book: books){
            for (CartBook cartBook: cartBookList){
                if(cartBook.getIdBook().equals(String.valueOf(book.getId())) && Integer.valueOf(cartBook.getValue())> book.getQuantity()
                || cartBook.getIdBook().equals(String.valueOf(book.getId())) && Integer.valueOf(cartBook.getValue()) < 1){
                    permitted = false;
                    break;
                }
            }
        }
        iKhanhBookService.addOrder(cartBookList, address, permitted);

        if (permitted){
            //Xóa giỏ hàng session
            if(!cartMap.isEmpty()){
                cartMap.clear();
            }
        }
    }

    @PostMapping("/update-cart")
    public ResponseEntity<String> updateCart(@RequestBody CartBook[] bookData, @SessionAttribute("cart") Map<Book, Integer> cartMap) {

        for (CartBook cartBook : bookData) {
            for (Book book : cartMap.keySet()) {
                if (book.getId().equals(Integer.valueOf(cartBook.getIdBook()))) {
                    if(Integer.valueOf(cartBook.getValue()) <= book.getQuantity() && Integer.valueOf(cartBook.getValue()) > 0){
                        cartMap.put(book, Integer.valueOf(cartBook.getValue()));
                    }else {
                        cartMap.put(book, 1);
                    }
                }
            }
        }
        return ResponseEntity.ok("Cart updated successfully");
    }
}
