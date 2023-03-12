package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.EmploymentMapper;
import com.example.springbootdemo1.domain.Employment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class EmploymentService {


    @Autowired
    EmploymentMapper employmentMapper;

    //根据page的信息完成
    public List<Employment> SelectByPage(int pageCount,int count){
        IPage<Employment> page = null;
        if(pageCount<=0 || count<=0){
            page = new Page<>();
        }else{
            page = new Page<>(pageCount,count);
        }
        IPage<Employment> groupDynamicIPage = employmentMapper.selectPage(page, null);
        return groupDynamicIPage.getRecords();
    }

    //模糊查询
    public List<Employment> SelectByKey(String date){
        QueryWrapper<Employment> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title",date);
        queryWrapper.or().like("content",date);
        return employmentMapper.selectList(queryWrapper);
    }



    //统计最大数据量
    public int getMaxCount(){
        return employmentMapper.selectCount(null);
    }

    //更新
    public int updateById(Employment e){
        return employmentMapper.updateById(e);
    }


    //删除
    public int deleteById(Employment e){
        return employmentMapper.deleteById(e.getId());
    }



}
