package com.example.springbootdemo1.Controller.Secure;

/**
 * @author 拾光
 * @version 1.0
 */

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.UserService;
import com.example.springbootdemo1.domain.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 需要登录后才能访问
 */
@Slf4j
@RestController
@Api(tags = "用户 API 接口 secure接口")
@RequestMapping("/secure")
@CrossOrigin   //跨域

public class SecureController {
    /**
     * 查询 用户信息，登录后才能访问
     */
    @RequestMapping("/getUserInfo")
    public String login(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        String name = request.getAttribute("name").toString();
        String userName = request.getAttribute("userName").toString();
        return "当前用户信息id=" + id + ",name=" + name + ",userName=" + userName;
    }

    @Autowired
    UserService userService;

    /**
     * 查询 用户信息，登录后才能访问
     */
    @RequestMapping("/findAll")
    public Result<List<User>> findAll(@Param("page")Integer page,@Param("count")Integer count){
        if(page==null || count==null){
            return Result.fail(null,"参数错误",404);
        }
        Result<List<User>> res = null;
        try{
            res = Result.success(userService.findAll(page,count),"成功",200);
        }catch (Exception e){
            res = Result.fail(null,e.getMessage(),404);
        }
        //日志
        log.debug(res.toString());
        return res;
    }

    @PostMapping("/findUserByName")
    public Result<User> findUserByName(@RequestBody User user){
        if(user==null){
            return Result.fail(null,"参数错误",404);
        }
        String username = user.getUsername();
        User userByName = userService.getUserByName(username);
        return Result.success(userByName,"用户获取成功",200);
    }




}
