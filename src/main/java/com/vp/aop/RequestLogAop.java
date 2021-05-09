package com.vp.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhoubeibei
 * @Description
 * @Date Create in 2019-08-02
 * @Modified By:
 */
@Aspect
@Slf4j
@Component
public class RequestLogAop {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void performance() {
    }

    @Before("performance()")
    public Object doBefore(JoinPoint joinPoint) {
        log.debug("=====================RequestLogAop start=====================");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String path = request.getServletPath();
        log.debug("请求地址：{}", path);
        String method = request.getMethod().toUpperCase();
        log.debug("请求方法类型：{}", method);
        String contentType = request.getContentType();
        log.debug("请求类型：{}", contentType);
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> requestHeaders = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(name);
            requestHeaders.put(name, headers.nextElement());
        }
        Map<String, String[]> params = request.getParameterMap();
        log.debug("请求参数：{}", params);

        log.debug("=====================RequestLogAop end=====================");
        return null;
    }
}
