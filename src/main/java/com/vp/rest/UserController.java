package com.vp.rest;

import com.vp.entity.User;
import com.vp.service.UserService;
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
    public User LoginUser(@RequestBody User user) {
        return userService.LoginUser(user);
    }

    @RequestMapping(value = "/updateUser", method = POST)
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
