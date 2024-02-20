package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Repository.IGiauBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiauBookService implements IGiauBookService{
    @Autowired
    private IGiauBookRepository bookRepository;
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Page<Book> getList(Pageable pageable) {
        return bookRepository.findAllBook(pageable);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
