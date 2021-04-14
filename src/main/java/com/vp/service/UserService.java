package com.vp.service;

import com.vp.entity.User;
import com.vp.vo.UserVo;
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
    UserVo LoginUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(User user);
}
