package com.vp.rest;

import com.vp.entity.User;
import com.vp.service.UserService;
import com.vp.util.BaseResponse;
import com.vp.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/loginUser", method = POST)
    public BaseResponse<UserVo> LoginUser(@RequestBody User user) {
        UserVo data = userService.LoginUser(user);
        return new BaseResponse<>(data);
    }

    @RequestMapping(value = "/updateUser", method = POST)
    public BaseResponse<Boolean> updateUser(@RequestBody User user) {
        boolean data = userService.updateUser(user);
        return new BaseResponse<>(data);
    }
}
