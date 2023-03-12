package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.NewsMapper;
import com.example.springbootdemo1.domain.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class NewsService {
    @Autowired
    NewsMapper newsMapper;

    public List<News> SelectByPage(int pageCount,int count){
        IPage<News> page = null;
        if(pageCount<=0 || count<=0){
            page = new Page<>();
        }else{
            page = new Page<>(pageCount,count);
        }
        IPage<News> newsIPage = newsMapper.selectPage(page, null);
        return newsIPage.getRecords();
    }


    //模糊查询
    public List<News> SelectByKey(String key){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",key);
        queryWrapper.or().like("content",key);
        return newsMapper.selectList(queryWrapper);
    }


    //模糊查询
    public List<News> SelectByDate(String date){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("timer",date);
        return newsMapper.selectList(queryWrapper);
    }

    //统计最大数据量
    public int getMaxCount(){
        return newsMapper.selectCount(null);
    }





    //更新
    public int updateById(News e){
        return newsMapper.updateById(e);
    }


    //删除
    public int deleteById(News e){

        return newsMapper.deleteById(e.getId());
    }

}
