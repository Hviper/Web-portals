package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.StudentMapper;
import com.example.springbootdemo1.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper mapper;

    public List<Student> SelectByData(String date){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("timer",date);
        return mapper.selectList(queryWrapper);
    }

    public List<Student> SelectByPage(Integer pageCount,Integer count){
        IPage<Student> page = null;
        if(pageCount<=0 || count<=0){
            page = new Page<>();
        }else{
            page = new Page<>(pageCount,count);
        }
        IPage<Student> groupDynamicIPage = mapper.selectPage(page, null);
        return groupDynamicIPage.getRecords();
    }

    //统计最大数据量
    public int getMaxCount(){
        return mapper.selectCount(null);
    }

    //更新
    public int updateById(Student e){
        return mapper.updateById(e);
    }


    //删除
    public int deleteById(Student e){
        return mapper.deleteById(e.getId());
    }

}
