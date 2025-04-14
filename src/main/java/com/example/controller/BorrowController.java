package com.example.controller;

import com.example.service.BookService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class BorrowController {

    @Resource
    BookService service;

    @Resource
    UserService userService;


    @RequestMapping({"/","/borrow"})
    public String borrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("borrow_list",service.getBorrowList());
        model.addAttribute("book_count",service.countBook());
        model.addAttribute("student_count", userService.countStudents());
        return "borrow";
    }

    @GetMapping("/add-borrow")
    public String addBorrow(Model model) {
        model.addAttribute("book_list",service.getAvailableBookList());
        model.addAttribute("student_list",userService.getStudents());
        return "add-borrow";

    }
    @PostMapping("/add-borrow")
    public String addBorrow(int student, int book) {
        service.insertBorrow(student,book);
        return "redirect:/borrow";

    }

    @GetMapping("/return-book")
    public String returnBook(int id){
        service.returnBorrowById(id);
        return "redirect:/borrow";
    }

}
