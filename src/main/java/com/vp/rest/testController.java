package com.vp.rest;

import com.vp.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class testController {
    @Autowired
    private testService testService;

    @RequestMapping("/test/{msg}")
    public String test(@PathVariable(value = "msg") String msg){
        return testService.getTest(msg);
    }
}
