package com.vp.service.impl;

import com.vp.dao.UserCompanyMapper;
import com.vp.entity.UserCompany;
import com.vp.service.UserCompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户公司映射-服务
 *
 * @author flybesttop
 * @date 2021-04-07
 */
@Slf4j
@Service
public class UserCompanyServiceImpl implements UserCompanyService {

    @Autowired
    public UserCompanyMapper userCompanyMapper;

    @Override
    public Boolean changeDefaultCompany(Integer defaultUserCompanyId, Integer oldDefaultUserCompanyId) {
        try {
            UserCompany userCompany = new UserCompany();
            userCompany.setId(defaultUserCompanyId);
            userCompany.setIsDefault(1);
            userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            userCompany.setId(oldDefaultUserCompanyId);
            userCompany.setIsDefault(0);
            userCompanyMapper.updateByPrimaryKeySelective(userCompany);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
