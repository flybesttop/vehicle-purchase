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
//    private Logger log = LoggerFactory.getLogger(getClass());

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
//        RequestLogDTO logDTO = new RequestLogDTO();
//        String clientIp = request.getHeader("X-Forwarded-For");
//        logDTO.setClientIp(clientIp);
//        logDTO.setClientName("门店后台");
//        logDTO.setClientType("SELLER");
//        logDTO.setHttpUrl(path);
//        logDTO.setHttpMethod(method);
//        if (StringUtils.equals("POST", method)) {
//            logDTO.setHttpBody(params);
//        } else {
//            logDTO.setHttpParam(params);
//        }
//        logDTO.setHttpHeader(requestHeaders);
//        String id = (String) request.getSession().getAttribute("id");
//        if (StringUtils.isNotBlank(id)) {
//            logDTO.setOrgId(Long.valueOf(id));
//        }
//        MyJdbcRealm.ShiroUser user = (MyJdbcRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
//        if (user != null) {
//            logDTO.setUsername(user.getLoginName());
//            Map sellerUser = userMapper.getSellerUserMapByUsername(user.getLoginName());
//            Integer userId = (Integer) sellerUser.get("id");
//            logDTO.setUserId(Long.valueOf(userId.toString()));
//        }
//
//
//        logDTO.setReqTime(LocalDateTime.now().toString());

//        try {
//            mqSender.sendRequestLog(logDTO);
//        } catch (Exception e) {
//            log.error("发送mq异常：{}", e.getMessage(), e);
//        }

        log.debug("=====================RequestLogAop end=====================");
        return null;
    }
}
