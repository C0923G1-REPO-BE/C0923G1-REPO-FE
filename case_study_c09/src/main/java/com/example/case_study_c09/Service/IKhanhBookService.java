package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Model.CartBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKhanhBookService {
    Page<Book> findAllBook(Pageable pageable);

    Book findBookById(Integer id);

    void addOrder(List<CartBook> cartBookList, String address);
}
