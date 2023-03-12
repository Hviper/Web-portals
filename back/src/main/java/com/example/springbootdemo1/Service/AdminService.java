package com.example.springbootdemo1.Service;

import com.example.springbootdemo1.Mapper.AdminMapper;
import com.example.springbootdemo1.domain.AdminUser;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 拾光
 * @version 1.0
 */
@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    //返回管理员id
    public Integer selectIdFromAds(AdminUser user){
        if(user==null){
            return null;
        }
        return adminMapper.selectIdFromAds(user.getUsername(),user.getPassword());
    }
}
