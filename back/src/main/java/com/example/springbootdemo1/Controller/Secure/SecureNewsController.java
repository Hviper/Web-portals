package com.example.springbootdemo1.Controller.Secure;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.NewsService;
import com.example.springbootdemo1.domain.GroupDynamic;
import com.example.springbootdemo1.domain.News;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author 拾光
 * @version 1.0
 */

@Slf4j
@RestController
@Api(tags = "groupDynamic API 接口 secure接口")
@RequestMapping("/secure/news")
@CrossOrigin   //跨域
public class SecureNewsController {

    @Autowired
    NewsService service;

    @PostMapping("/update")
    public Result<?> updateById(@RequestBody News e){
        if(e==null){
            return Result.fail(null,"参数错误",404);
        }
        int id = service.updateById(e);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",id);
        map.put("count",service.getMaxCount());
        return Result.success(map,"更新新闻信息成功",200);
    }

    @PostMapping("/delete")
    public Result<?> deleteById(@RequestBody News e){
        if(e==null){
            return Result.fail(null,"参数错误",404);
        }
        int id = service.deleteById(e);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",id);
        map.put("count",service.getMaxCount());
        return Result.success(map,"删除新闻信息成功",200);
    }
}
