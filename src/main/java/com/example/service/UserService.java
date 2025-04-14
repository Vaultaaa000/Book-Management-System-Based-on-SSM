package com.example.service;

import com.example.entity.Student;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<Student> getStudents();
    int countStudents();
}
