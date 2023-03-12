package com.example.springbootdemo1.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootdemo1.domain.User;
/**
 * @author 拾光
 * @version 1.0
 */
//@Mapper   //启动类中加了MapperScan全局注解
//@Repository
public interface UserMapper extends BaseMapper<User> {

}
