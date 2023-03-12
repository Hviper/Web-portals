package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author 拾光
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("student_style")
public class Student {
    @TableId(type = AUTO)
    private Integer id;
    private String title;
    private String content;
    private String timer;
    private String author;
}
