package com.example.service.impl;

import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;

    @Override
    public List<Borrow> getBorrowList() {
        return bookMapper.getBorrow();

    }

    @Override
    public List<Book> getBookList() {
        return bookMapper.getBookList();
    }

    @Override
    public List<Book> getAvailableBookList() {
        return bookMapper.getAvailableBookList();
    }

    @Override
    public int countBook() {
        return bookMapper.countBook();
    }

    @Override
    public void addBook(String title, String desc, double price, int bnum) {
        bookMapper.insertBook(title, desc, price, bnum);

    }

    @Override
    public void deleteBookById(int bid) {
        bookMapper.deleteBookById(bid);
    }

    @Override
    public void insertBorrow(int sid, int bid) {
        bookMapper.insertBorrow(sid, bid);

    }

    @Override
    public void returnBorrowById(int id) {
        bookMapper.deleteBorrow(id);
    }


}
