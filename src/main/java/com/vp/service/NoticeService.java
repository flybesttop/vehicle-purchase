package com.vp.service;

import com.vp.entity.Notice;
import com.vp.dto.NoticeDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告服务类
 *
 * @author flybesttop
 * @date 2021-05-09
 */
@Service
public interface NoticeService {
    /**
     * 发布公告
     *
     * @param req
     * @return
     */
    Boolean publishNotice(NoticeDto req);

    /**
     * 获取公告列表
     *
     * @param searchKey
     * @param openId
     * @return
     */
    List<Notice> getNoticeList(String searchKey, String openId);

    /**
     * 获取公告详情
     *
     * @param noticeId
     * @return
     */
    NoticeDto getNoticeInfo(Integer noticeId);

    /**
     * 删除公告图
     *
     * @param noticeId
     * @return
     */
    Boolean deleteNoticeImgs(Integer noticeId);

    /**
     * 删除公告
     *
     * @param noticeId
     * @return
     */
    Boolean deleteNotice(Integer noticeId);
}
