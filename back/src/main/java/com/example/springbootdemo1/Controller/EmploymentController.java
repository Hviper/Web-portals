package com.example.springbootdemo1.Controller;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.EmploymentService;
import com.example.springbootdemo1.domain.Employment;
import com.example.springbootdemo1.domain.News;
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
@CrossOrigin   //跨域
@RestController
@RequestMapping("/employments")
public class EmploymentController {

    @Autowired
    EmploymentService employmentService;

    @RequestMapping("/info")
    public Result<?> SelectByPage(@Param("page") Integer page, @Param("count")Integer count){
        if(page==null || count==null){
            return Result.fail(null,"参数错误",404);
        }


        List<Employment> groupDynamics = employmentService.SelectByPage(page, count);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",groupDynamics);
        map.put("count",employmentService.getMaxCount());
        return Result.success(map,"获取就业信息成功",200);

    }

    @RequestMapping("/search")
    public Result<?> SelectByKey(@Param("keyword") String keyword){
        if(keyword==null ){
            return Result.fail(null,"参数错误",404);
        }
        List<Employment> news = employmentService.SelectByKey(keyword);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",news);
        map.put("count",employmentService.getMaxCount());
        return Result.success(map,"获取新闻成功",200);
    }


}
