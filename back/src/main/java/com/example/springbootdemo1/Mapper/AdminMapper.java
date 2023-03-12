package com.example.springbootdemo1.Mapper;

import org.apache.ibatis.annotations.Select;
/**
 * @author 拾光
 * @version 1.0
 */
public interface AdminMapper {

    @Select("select id from admin where username=#{username} and password=#{password}")
    Integer selectIdFromAds(String username,String password);
}
