package com.vp.service;

import com.vp.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@Service
public interface UserService {
    User LoginUser(User user);
    boolean updateUser(User user);
}
