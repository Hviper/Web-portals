package com.example.springbootdemo1.Controller;

import com.example.springbootdemo1.Response.Result;
import com.example.springbootdemo1.Service.GroupDynamicService;
import com.example.springbootdemo1.Service.NoticeService;
import com.example.springbootdemo1.domain.GroupDynamic;
import com.example.springbootdemo1.domain.Notice;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@CrossOrigin   //跨域
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    NoticeService service;


    @RequestMapping("/info")
    public Result<?> SelectByPage(@Param("page") Integer page, @Param("count")Integer count){
        if(page==null || count==null){
            return Result.fail(null,"参数错误",404);
        }
        List<Notice> groupDynamics = service.SelectByPage(page, count);

        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",groupDynamics);
        map.put("count",service.getMaxCount());
        return Result.success(map,"获取新闻成功",200);
    }


    @RequestMapping("/search/date")
    public Result<?> SelectByDate(@Param("timerData") String timerData){
        if(timerData==null ){
            return Result.fail(null,"参数错误",404);
        }
        List<Notice> news = service.SelectByDate(timerData);
        HashMap<Object,Object> map = new HashMap<>();
        map.put("data",news);
        map.put("count",service.getMaxCount());
        return Result.success(map,"获取新闻成功",200);
    }
}
