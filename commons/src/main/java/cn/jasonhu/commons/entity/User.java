package cn.jasonhu.commons.entity;

import java.util.Date;

import lombok.Data;

@Data
public class User {

    private int id;

    private String username;

    private String password;

    private Date createTime;
}
