package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 拾光
 * @version 1.0
 */
@TableName("admin")
@Data
public class AdminUser {
    private int id;
    private String username;
    private String password;
}
