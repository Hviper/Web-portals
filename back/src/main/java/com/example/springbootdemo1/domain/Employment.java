package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 拾光
 * @version 1.0
 */
@Data
@TableName("employmentinfo")
public class Employment {
    private int id;
    private String title;
    private String content;
}
