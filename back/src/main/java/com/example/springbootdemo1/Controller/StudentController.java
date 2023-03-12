package com.example.springbootdemo1.Controller;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.StudentService;
import com.example.springbootdemo1.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@RestController
@RequestMapping("/student")
@CrossOrigin           //跨域啊。。。。。
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/byDate")
    public Result<?> SelectByData(@Param("timerData") String timerData){
        if(timerData==null ){
            return Result.fail(null,"参数错误",404);
        }
        List<Student> news = studentService.SelectByData(timerData);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",news);
        map.put("count",studentService.getMaxCount());
        return Result.success(map,"获取学生信息成功",200);
    }
    @RequestMapping("/info")
    public Result<?> SelectByPage(@Param("page") Integer page,@Param("count") Integer count){
        if(page==null || count==null){
            return Result.fail(null,"参数错误",404);
        }
        List<Student> groupDynamics = studentService.SelectByPage(page, count);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",groupDynamics);
        map.put("count",studentService.getMaxCount());
        return Result.success(map,"获取新闻成功",200);
    }
}
