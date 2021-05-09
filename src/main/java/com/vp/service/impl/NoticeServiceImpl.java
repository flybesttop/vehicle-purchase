package com.vp.service.impl;

import com.vp.dao.CompanyMapper;
import com.vp.dao.NoticeImgsMapper;
import com.vp.dao.NoticeMapper;
import com.vp.dao.UserMapper;
import com.vp.entity.Company;
import com.vp.entity.Notice;
import com.vp.entity.NoticeImgs;
import com.vp.entity.User;
import com.vp.dto.NoticeDto;
import com.vp.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 公告服务实现类
 *
 * @author flybesttop
 * @date 2021-05-09
 */
@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeImgsMapper noticeImgsMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean publishNotice(NoticeDto req) {
        try {
            Company company = companyMapper.getDefaultCompanyInfo(req.getOpenId());
            User user = userMapper.selectByPrimaryKey(req.getOpenId());
            Notice notice = new Notice();
            notice.setId(req.getNoticeId());
            notice.setCompanyId(company.getId());
            notice.setContent(req.getContent());
            notice.setImgUrl(req.getIndexImg());
            notice.setOperatorId(req.getOpenId());
            notice.setOperatorName(user.getUsername());
            notice.setTitle(req.getTitle());
            if (Objects.nonNull(notice.getId())){
                noticeMapper.updateByPrimaryKey(notice);
                noticeImgsMapper.deleteAllNoticeImgs(notice.getId());
            }else{
                noticeMapper.insertSelective(notice);
            }

            if (!CollectionUtils.isEmpty(req.getImgList())) {
                for (int i = 0; i < req.getImgList().size(); i++) {
                    NoticeImgs noticeImgs = new NoticeImgs();
                    noticeImgs.setImgUrl(req.getImgList().get(i));
                    noticeImgs.setNoticeId(notice.getId());
                    noticeImgs.setOperatorId(user.getOpenId());
                    noticeImgs.setOperatorName(user.getUsername());
                    noticeImgs.setSort(i);
                    noticeImgsMapper.insertSelective(noticeImgs);
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Notice> getNoticeList(String searchKey, String openId) {
        Company company = companyMapper.getDefaultCompanyInfo(openId);
        return noticeMapper.getNoticeList(searchKey, company.getId());
    }

    @Override
    public NoticeDto getNoticeInfo(Integer noticeId) {
        Notice notice = noticeMapper.selectByPrimaryKey(noticeId);
        User user=userMapper.selectByPrimaryKey(notice.getOperatorId());
        List<NoticeImgs> noticeImgs=noticeImgsMapper.getNoticeImgs(noticeId);
        List<String> noticeImgUrls=new ArrayList<>();
        noticeImgs.forEach(ni -> noticeImgUrls.add(ni.getImgUrl()));
        NoticeDto noticeDto=new NoticeDto();
        noticeDto.setContent(notice.getContent());
        noticeDto.setImgList(noticeImgUrls);
        noticeDto.setIndexImg(notice.getImgUrl());
        noticeDto.setNoticeId(noticeId);
        noticeDto.setTitle(notice.getTitle());
        noticeDto.setOperatorName(user.getUsername());
        noticeDto.setOperatorImg(user.getAvatarUrl());
        noticeDto.setOperatorTime(notice.getUpdateTime());
        return noticeDto;
    }

    @Override
    public Boolean deleteNoticeImgs(Integer noticeId) {
        try {
            noticeImgsMapper.deleteAllNoticeImgs(noticeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean deleteNotice(Integer noticeId) {
        try {
            noticeImgsMapper.deleteAllNoticeImgs(noticeId);
            noticeMapper.deleteByPrimaryKey(noticeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
