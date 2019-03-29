package com.example.springbootdemo.service;



import com.example.springbootdemo.dao.StudentMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value="studentService")
public class StudentSerivce implements IStudentSerivce{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List findAllUser() {
        return studentMapper.findAllUser();

    }


}
