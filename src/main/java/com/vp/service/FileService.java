package com.vp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author flybesttop
 * @date 2020/12/1
 */
@Service
public interface FileService {
    void uploadMultipartFileImage(Map<String, Object> data, MultipartFile file);
}
