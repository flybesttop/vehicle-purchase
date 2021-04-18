package com.vp.rest;

import com.vp.service.FileService;
import com.vp.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author flybesttop
 * @date 2020/12/1
 */
@Slf4j
@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("uploadImage")
    public BaseResponse<Map<String,Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> data = new HashMap<>();
        fileService.uploadMultipartFileImage(data, file);
        return new BaseResponse<>(data);
    }
}
