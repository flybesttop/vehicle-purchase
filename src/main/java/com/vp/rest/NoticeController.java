package com.vp.rest;

import com.vp.entity.Notice;
import com.vp.dto.NoticeDto;
import com.vp.service.NoticeService;
import com.vp.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 公告控制类
 *
 * @author flybesttop
 * @date 2021-05-09
 */
@Slf4j
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "publishNotice", method = POST)
    public BaseResponse<Boolean> publishNotice(@RequestBody NoticeDto req) {
        Boolean res = noticeService.publishNotice(req);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "getNoticeList", method = GET)
    public BaseResponse<List<Notice>> getNoticeList(String searchKey, String openId) {
        List<Notice> res = noticeService.getNoticeList(searchKey, openId);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "getNoticeInfo", method = GET)
    public BaseResponse<NoticeDto> getNoticeInfo(Integer noticeId){
        NoticeDto res = noticeService.getNoticeInfo(noticeId);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "deleteNoticeImgs", method = GET)
    public BaseResponse<Boolean> deleteNoticeImgs(Integer noticeId){
        Boolean res = noticeService.deleteNoticeImgs(noticeId);
        return new BaseResponse<>(res);
    }

    @RequestMapping(value = "deleteNotice", method = GET)
    public BaseResponse<Boolean> deleteNotice(Integer noticeId){
        Boolean res = noticeService.deleteNotice(noticeId);
        return new BaseResponse<>(res);
    }

}
