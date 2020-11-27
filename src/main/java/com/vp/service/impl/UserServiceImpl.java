package com.vp.service.impl;

import com.vp.dao.UserMapper;
import com.vp.entity.User;
import com.vp.service.UserService;
import com.vp.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User LoginUser(User user) {
        User checkUser=userMapper.selectByPrimaryKey(user.getOpenId());
        if (CommonUtils.isEmpty(checkUser)){
            userMapper.insert(user);
            return user;
        } else {
            return checkUser;
        }
    }

    @Override
    public boolean updateUser(User user) {
        int result=userMapper.updateByPrimaryKeySelective(user);
        return result!=0;
    }
}
