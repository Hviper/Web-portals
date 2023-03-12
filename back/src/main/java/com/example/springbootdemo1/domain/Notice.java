package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 拾光
 * @version 1.0
 */
@TableName("noticeinfo")
@Data
public class Notice {
    private int id;
    private String title;
    private String content;
    private String timer;
}
