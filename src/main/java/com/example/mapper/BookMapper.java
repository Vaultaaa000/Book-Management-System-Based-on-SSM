package com.example.mapper;

import com.example.entity.Book;
import com.example.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "sid", property = "sid"),
            @Result(column = "time", property = "time"),
            @Result(column = "title", property = "bookName"),
            @Result(column = "name", property = "studentName"),
    })
    @Select("""
        select * from borrow left join student on borrow.sid = student.sid
        left join book on borrow.bid = book.bid
        """)
    List<Borrow> getBorrow();

    @Insert("insert into borrow (sid, bid, time) values (#{sid},#{bid},NOW())")
    void insertBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(@Param("id") int id);


    @Select("select count(*) from book")
    int countBook();


    @Select("select * from book where total_number > 0")
    List<Book> getAvailableBookList();

    @Result(column = "total_number", property = "bnum")
    @Select("select * from book")
    List<Book> getBookList();

    @Delete("delete from book where bid = #{bid}")
    void deleteBookById(int bid);

    @Insert("insert into book (title, `desc`, price, total_number) VALUES (#{title},#{desc},#{price},#{bnum})")
    void insertBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price,
                    @Param("bnum") int bnum);
}
