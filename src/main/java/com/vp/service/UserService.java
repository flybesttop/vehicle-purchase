package com.vp.service;

import com.vp.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@Service
public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User LoginUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);
}
