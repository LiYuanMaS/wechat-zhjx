package com.example.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbootdemo.service.IStudentSerivce;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class StudentAction
{
    @Autowired
    private IStudentSerivce studentService;
    @RequestMapping(value={"/findAll"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
    public List getAllUsers(){
        List list = studentService.findAllUser();
        return list;
    }

        @RequestMapping(value={"/update"},produces = {"application/json;charset=UTF-8"},method = RequestMethod.GET)
        public String update(){

            return "hello world";
    }

}
