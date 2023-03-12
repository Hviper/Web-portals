package com.example.springbootdemo1.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 拾光
 * @version 1.0
 */
@TableName("group_dynamics")
@Data
public class GroupDynamic {
    private int id;
    private String title;
    private String imgUrl;
    private String content;
    private String timer;
}
