package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IKhanhBookRepository extends JpaRepository<Book, Integer> {
    @Query(value = "select * from book where is_delete = false", nativeQuery = true)
    Page<Book> findAllBook (Pageable pageable);

    Page<Book> findByNameBookContaining(String name, Pageable pageable);

    @Query(value = "select book.* from book where category_id = :id", nativeQuery = true)
    Page<Book> findAllBookByCategory(@Param("id")Integer sort, Pageable bookPageable);
}
