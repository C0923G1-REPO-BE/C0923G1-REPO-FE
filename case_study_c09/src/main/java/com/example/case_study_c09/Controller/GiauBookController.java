package com.example.case_study_c09.Controller;

import com.example.case_study_c09.DTO.BookDTO;
import com.example.case_study_c09.Model.Author;
import com.example.case_study_c09.Model.Book;
import com.example.case_study_c09.Model.Category;
import com.example.case_study_c09.Service.IGiauAuthorService;
import com.example.case_study_c09.Service.IGiauBookService;
import com.example.case_study_c09.Service.IGiauCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/book")
public class GiauBookController {
    @Autowired
    private IGiauBookService iGiauBookService;
    @Autowired
    private IGiauCategoryService iGiauCategoryService;
    @Autowired
    private IGiauAuthorService iGiauAuthorService;

    @ModelAttribute("category")
    public List<Category> category() {
        return iGiauCategoryService.findAll();
    }

    @ModelAttribute("author")
    public List<Author> author() {
        return iGiauAuthorService.findAll();
    }

    @GetMapping("")
    public String show(@PageableDefault(value = 10) Pageable pageable, Model model) {
        Page<Book> books = iGiauBookService.getList(pageable);
        model.addAttribute("book", books);
        return "home-admin";
    }

    @GetMapping("/lock")
    public String showLock(@PageableDefault(value = 10) Pageable pageable, Model model) {
        Page<Book> books = iGiauBookService.getListLock(pageable);
        model.addAttribute("book", books);
        return "list-book-lock";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        Book book = iGiauBookService.findById(id);
        book.setDelete(true);
        iGiauBookService.save(book);
        return "redirect:/book";
    }

    @PostMapping("/unlock")
    public String unlock(@RequestParam("id") int id) {
        Book book = iGiauBookService.findById(id);
        book.setDelete(false);
        iGiauBookService.save(book);
        return "redirect:/book/lock";
    }

    @GetMapping("/create")
    public String add(Model model) {
        model.addAttribute("book", new BookDTO());
        return "add-book";
    }

    @PostMapping("/create")
    public String addBook(@Valid @ModelAttribute("book") BookDTO bookDTO, BindingResult bindingResult,
                          Model model, RedirectAttributes redirectAttributes) {
        new BookDTO().validate(bookDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("bookDTO", bookDTO);
            return "add-book";
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        iGiauBookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("book", iGiauBookService.findById(id));
        return "edit-book";
    }

    @PostMapping("edit")
    public String editBook(@Valid @ModelAttribute("book") BookDTO bookDTO, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes) {
        new BookDTO().validate(bookDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("bookDTO", bookDTO);
            return "edit-book";
        }
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO, book);
        iGiauBookService.save(book);
        return "redirect:/book";
    }

    @PostMapping("search")
    public String search(@RequestParam("name-search") String name,
                         @PageableDefault(value = 4) Pageable pageable,
                         Model model) {
        Page<Book> books = iGiauBookService.findByNameBookContaining(name, pageable);
        model.addAttribute("book", books);
        return "home-admin";
    }
}
