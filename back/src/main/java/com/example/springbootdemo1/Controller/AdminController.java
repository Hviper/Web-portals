package com.example.springbootdemo1.Controller;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.AdminService;
import com.example.springbootdemo1.Service.UserService;
import com.example.springbootdemo1.Utils.JwtUtil;
import com.example.springbootdemo1.domain.AdminUser;
import com.example.springbootdemo1.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 拾光
 * @version 1.0
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody AdminUser user){
        if(user==null){
            return Result.fail(null,"参数错误，登录失败",404);
        }
        Integer i = adminService.selectIdFromAds(user);
        if(i==null){
            return Result.fail(null,"登录失败,数据库中无该用户任何数据",404);
        }
        Map<String,String> map = new HashMap<>();
        String token = JwtUtil.createTokenByAdmin(user);
        log.debug("请求的user信息："+user);
        map.put("token",token);
        return Result.success(map,"登录成功",200);
    }


    @Autowired
    UserService userService;

    @RequestMapping("/allUser")
    public Result<?> findAll(@Param("page") Integer page, @Param("count")Integer count){
        if(page==null || count==null){
            return Result.fail(null,"参数错误",404);
        }
        Map<Object,Object> map = new HashMap<>();
        List<User> all = userService.findAll(page, count);
        map.put("data",all);
        map.put("count",all.size());
        return Result.success(map,"请求成功",404);
    }
}
