package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGiauBookService {
    List<Book> findAll();

    Book findById(int id);

    void save(Book book);

    Page<Book> getList(Pageable pageable);

    Page<Book> getListLock(Pageable pageable);

    void delete(Book book);

    Page<Book> findByNameBookContaining(String name, Pageable pageable);
}
