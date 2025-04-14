package com.example.controller;

import com.example.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Resource
    BookService bookService;

    @GetMapping("/books")
    public String books(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("book_list",bookService.getBookList());
        return "books";
    }

    @GetMapping("/add-book")
    public String addBook() {
        return "add-book";

    }

    @PostMapping("/add-book")
    public String addBook(String title, String desc, double price, int bnum){
        bookService.addBook(title,desc,price,bnum);
        return "redirect:/books";
    }

    @GetMapping("delete-book")
    public String deleteBook(int bid){
        bookService.deleteBookById(bid);
        return "redirect:/books";

    }

}
