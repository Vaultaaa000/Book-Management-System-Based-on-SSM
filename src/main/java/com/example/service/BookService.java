package com.example.service;

import com.example.entity.Book;
import com.example.entity.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    List<Borrow> getBorrowList();
    List<Book> getBookList();
    List<Book> getAvailableBookList();
    int countBook();
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price, @Param("bnum") int bnum);
    void deleteBookById(int bid);
    void insertBorrow(@Param("sid") int sid, @Param("bid") int bid);
    void returnBorrowById(@Param("id")int id);


}
