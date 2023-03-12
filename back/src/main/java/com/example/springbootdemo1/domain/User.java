package com.example.springbootdemo1.domain;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("user")
//@Component("user1")  //springboot的ComponentScan这个注解是扫描@Component注解并将其加入到IOC容器当中
public class User {
    @TableId(type = AUTO)
    private Integer id;
    @TableField("realName")
    private String realName;
    private String username;
    private String password;
    private String gender;
    private java.util.Date birthday;
    private String email;
    @TableField("telephoneNumber")
    private String telephoneNumber;
}
