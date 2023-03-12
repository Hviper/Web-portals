package com.example.springbootdemo1.Controller.Secure;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.GroupDynamicService;
import com.example.springbootdemo1.domain.Employment;
import com.example.springbootdemo1.domain.GroupDynamic;
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
@RequestMapping("/secure/groupDynamic")
@CrossOrigin   //跨域
public class SecureGroupDynamicController {
    @Autowired
    GroupDynamicService service;


    @PostMapping("/update")
    public Result<?> updateById(@RequestBody GroupDynamic e){
        if(e==null){
            return Result.fail(null,"参数错误",404);
        }
        int id = service.updateById(e);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",id);
        map.put("count",service.getMaxCount());
        return Result.success(map,"更新就业信息成功",200);
    }

    @PostMapping("/delete")
    public Result<?> deleteById(@RequestBody GroupDynamic e){
        if(e==null){
            return Result.fail(null,"参数错误",404);
        }
        int id = service.deleteById(e);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",id);
        map.put("count",service.getMaxCount());
        return Result.success(map,"删除就业信息成功",200);
    }
}
