package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.NoticeMapper;
import com.example.springbootdemo1.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class NoticeService {
    @Autowired
    NoticeMapper mapper;
    public List<Notice> SelectByPage(int pageCount, int count){
        IPage<Notice> page = null;
        if(pageCount<=0 || count<=0){
            page = new Page<>();
        }else{
            page = new Page<>(pageCount,count);
        }
        IPage<Notice> newsIPage = mapper.selectPage(page, null);
        return newsIPage.getRecords();
    }

    //模糊查询
    public List<Notice> SelectByDate(String date){
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("timer",date);
        return mapper.selectList(queryWrapper);
    }

    //统计最大数据量
    public int getMaxCount(){
        return mapper.selectCount(null);
    }


    //更新
    public int updateById(Notice e){
        return mapper.updateById(e);
    }


    //删除
    public int deleteById(Notice e){

        return mapper.deleteById(e.getId());
    }

}
