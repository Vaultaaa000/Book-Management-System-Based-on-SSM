package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    Account selectAccountByName(String username);

    @Select("select * from student")
    List<Student> selectAllStudent();

    @Select("select count(*) from student")
    int countStudent();
}
