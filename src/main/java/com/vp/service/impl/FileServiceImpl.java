package com.vp.service.impl;

import com.vp.service.FileService;
import com.zlzkj.core.util.UploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author flybesttop
 * @date 2020/12/1
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void uploadMultipartFileImage(Map<String, Object> data, MultipartFile file) {
        String picWeb = "";
        // 图片保存名
        if (null != file) {
            Map<String, Object> imageFileInfo = UploadUtils.saveMultipartFile(file);
            if ((Integer) imageFileInfo.get("status") > 0) {
                //上传完成
                picWeb = UploadUtils.parseFileUrl(imageFileInfo.get("saveName").toString());
            } else { //上传失败
                data.put("errorMessage", imageFileInfo.get("errorMsg").toString());
            }
            data.put("imgUrl", imageFileInfo.get("saveName").toString());
            data.put("src", picWeb);
        }

    }
}
