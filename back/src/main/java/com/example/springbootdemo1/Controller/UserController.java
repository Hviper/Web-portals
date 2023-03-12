package com.example.springbootdemo1.Controller;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.UserService;
import com.example.springbootdemo1.Utils.JwtUtil;
import com.example.springbootdemo1.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 拾光
 * @version 1.0
 */
@RequestMapping("/user")
@RestController    //里面是ResponseBody注解，使得返回前台response数据为json字符串数据
@CrossOrigin    //允许跨域5
@Api(tags = "用户 API 接口")
@Slf4j   //== private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class);
public class UserController {

//    private final Logger log = LoggerFactory.getLogger(UserController.class);   //有@Slf4j注解，简化这步操作
    @Autowired
    UserService userService;

    //RequestParam指定参数
//    @PostMapping("/test")
//    public Result<?> Test(@RequestParam(name="password",defaultValue = "root") String password,
//                     @RequestParam(name="username",defaultValue = "root") String username,
//                     @RequestParam(name="sex",required = false) String sex){
//        System.out.println(password);
//        System.out.println(username);
//        System.out.println(sex);
//        return Result.success(password,"成功",200);
//    }

    @PostMapping("/login")
    @ApiOperation(value = "登录测试", notes = "目前仅仅是作为测试，用于测试登录token的获取")
    @ApiImplicitParam(name = "user", value = "用户信息", paramType = "paramType", dataTypeClass = User.class, required = true, example = "{\"username\":\"apache\",\"password\":\"4444444444\"}")
    public Result<?> login(@RequestBody User user){   //@RequestBody 将请求的json字符串转为对象
        User userById = userService.getUserByName(user.getUsername());
        if(userById==null || !Objects.equals(userById.getPassword(),user.getPassword())){
            return Result.fail(null,"无对应用户",404);
        }
        Map<String,String> map = new HashMap<>();
        String token = JwtUtil.createToken(user);
        log.debug("请求的user信息："+user);
        map.put("token",token);
        return Result.success(map,"登录成功",200);
    }


    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
        if(user==null){
            return Result.fail(null,"参数错误",404);
        }
        int i = userService.addOneUser(user);
        return Result.success(i,"添加成功",200);
    }





}
