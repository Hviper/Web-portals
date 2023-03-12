package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 拾光
 * @version 1.0
 */
@TableName("news_list")
@Data
public class News {
    private int id;
    private String title;
    private String content;
    private String timer;
}
