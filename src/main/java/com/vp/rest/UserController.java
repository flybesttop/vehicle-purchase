package com.vp.rest;

import com.vp.entity.User;
import com.vp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author flybesttop
 * @date 2020/11/25
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/loginUser",method = POST)
    public User LoginUser(HttpServletRequest request,@RequestBody User user){
        return userService.LoginUser(user);
    }

    @RequestMapping(value = "/updateUser",method = POST)
    public boolean updateUser(HttpServletRequest request,@RequestBody User user){
        return userService.updateUser(user);
    }
}
