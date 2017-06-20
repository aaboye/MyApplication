package com.ren.tarena.xiaohua.entity;

/**
 * Created by tarena on 2017/6/3.
 */

public class User {
    private int id;
    private String loginname;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginname='" + loginname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(int id, String loginname, String password) {
        this.id = id;
        this.loginname = loginname;
        this.password = password;
    }
}
