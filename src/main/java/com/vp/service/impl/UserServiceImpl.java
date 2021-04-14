package com.vp.service.impl;

import com.vp.dao.NodeMapper;
import com.vp.dao.UserMapper;
import com.vp.entity.Node;
import com.vp.entity.User;
import com.vp.service.UserService;
import com.vp.util.CommonUtils;
import com.vp.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public UserVo LoginUser(User user) {
        User checkUser = userMapper.selectByPrimaryKey(user.getOpenId());
        UserVo userVo = new UserVo();
        if (CommonUtils.isEmpty(checkUser)) {
            userMapper.insert(user);
            userVo.setUser(user);
        } else {
            userVo.setUser(checkUser);
        }
        List<Node> nodes=nodeMapper.getUserDefaultCompanyRoleNodes(user.getOpenId());
        userVo.setNodes(nodes);
        return userVo;
    }

    @Override
    public boolean updateUser(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        return result != 0;
    }
}
