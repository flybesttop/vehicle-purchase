package com.vp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源路径映射配置
 * 所有以staticAccessPath开头的静态资源访问都会访问uploadFolder路径中内容
 *
 * @author flybesttop
 * @date 2020/12/1
 *
 * 过时类 WebMvcConfigurerAdapter
 */
@Configuration
public class UploadFilePathConfig implements WebMvcConfigurer {

    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
    }
}
