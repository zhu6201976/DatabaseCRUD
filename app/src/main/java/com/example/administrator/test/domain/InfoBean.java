package com.example.administrator.test.domain;

/**
 * Created by My on 2017/12/8.
 */

public class InfoBean {
    public int _id;
    public String name;
    public String phone;

    @Override
    public String toString() {
        return "InfoBean{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
