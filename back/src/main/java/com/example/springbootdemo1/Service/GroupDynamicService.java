package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.GroupDynamicMapper;
import com.example.springbootdemo1.domain.GroupDynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class GroupDynamicService {

    @Autowired
    GroupDynamicMapper groupDynamicMapper;


    //根据page的信息完成
    public List<GroupDynamic> SelectByPage(int pageCount,int count){
        IPage<GroupDynamic> page = null;
        if(pageCount<=0 || count<=0){
            page = new Page<>();
        }else{
            page = new Page<>(pageCount,count);
        }
        IPage<GroupDynamic> groupDynamicIPage = groupDynamicMapper.selectPage(page, null);
        return groupDynamicIPage.getRecords();
    }

    //模糊查询
    public List<GroupDynamic> SelectByDate(String date){
        QueryWrapper<GroupDynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("timer",date);
        return groupDynamicMapper.selectList(queryWrapper);
    }

    //统计最大数据量
    public int getMaxCount(){
        return groupDynamicMapper.selectCount(null);
    }


    //更新
    public int updateById(GroupDynamic e){
        return groupDynamicMapper.updateById(e);
    }


    //删除
    public int deleteById(GroupDynamic e){

        return groupDynamicMapper.deleteById(e.getId());
    }
}
