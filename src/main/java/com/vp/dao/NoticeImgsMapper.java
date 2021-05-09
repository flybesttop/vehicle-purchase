package com.vp.dao;

import com.vp.entity.NoticeImgs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeImgsMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteAllNoticeImgs(Integer noticeId);

    int insert(NoticeImgs record);

    int insertSelective(NoticeImgs record);

    NoticeImgs selectByPrimaryKey(Integer id);

    List<NoticeImgs> getNoticeImgs(Integer noticeId);

    int updateByPrimaryKeySelective(NoticeImgs record);

    int updateByPrimaryKey(NoticeImgs record);
}