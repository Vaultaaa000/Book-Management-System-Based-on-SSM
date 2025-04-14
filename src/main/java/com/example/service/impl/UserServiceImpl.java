package com.example.service.impl;

import com.example.entity.Account;
import com.example.entity.Student;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username){
        Account account = mapper.selectAccountByName(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名或密码输入错误");
        }
        return User.withUsername(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    @Override
    public List<Student> getStudents() {
        return mapper.selectAllStudent();
    }

    @Override
    public int countStudents() {
        return mapper.countStudent();
    }
}
