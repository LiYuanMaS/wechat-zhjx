package com.example.springbootdemo.pojo;

import java.util.List;

public class Student {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test.userid
     *
     * @mbg.generated
     */
    private String userid;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column test.age
     *
     * @mbg.generated
     */
    private Integer age;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test.userid
     *
     * @return the value of test.userid
     *
     * @mbg.generated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test.userid
     *
     * @param userid the value for test.userid
     *
     * @mbg.generated
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test.username
     *
     * @return the value of test.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test.username
     *
     * @param username the value for test.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column test.age
     *
     * @return the value of test.age
     *
     * @mbg.generated
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column test.age
     *
     * @param age the value for test.age
     *
     * @mbg.generated
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}