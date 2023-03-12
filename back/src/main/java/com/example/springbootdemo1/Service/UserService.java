package com.example.springbootdemo1.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootdemo1.Mapper.UserMapper;
import com.example.springbootdemo1.domain.User;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
@Transactional    //begin开启一个事务
public class UserService {
    @Autowired
    UserMapper userMapper;


    /**
     * 添加一个用户
     * @param user
     * @return
     */
    public int addOneUser(User user){
        if(user==null){
            return -1;
        }
        return userMapper.insert(user);
    }

    public List<User> findAll(int page,int count){
        List<User> res = null;
        IPage<User> p = new Page<>(page,count);
        try{
            res = userMapper.selectPage(p,null).getRecords();
            if(res==null){
                throw new RuntimeException("查找失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    /**
     *  NonNull注解： lombok注解
     *  添加在方法参数、类属性上，用于自动生成 null 参数检查。若确实是 null 时，抛出 NullPointerException 异常。
     */
    public User getUserById(@NonNull long id){
        return userMapper.selectById(id);
    }

    public User getUserByName(@NonNull String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        return userMapper.selectOne(queryWrapper.eq(true,"username",username));
    }


    //更新
    public int updateById(User e){
        return userMapper.updateById(e);
    }


    //删除
    public int deleteById(User e){
        return userMapper.deleteById(e.getId());
    }

    public int getMaxCount() {
        return userMapper.selectCount(null);
    }
}
