package com.vp.service.impl;

import com.vp.service.testService;
import org.springframework.stereotype.Service;

@Service
public class testServiceImpl implements testService {
    @Override
    public String getTest(String restmsg) {
        return restmsg;
    }
}
