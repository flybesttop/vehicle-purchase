package com.vp.service;

import org.springframework.stereotype.Service;

/**
 * 用户公司映射-服务
 *
 * @author flybesttop
 * @date 2021-04-07
 */
@Service
public interface UserCompanyService {

    /**
     * 修改默认公司
     *
     * @param defaultCompanyId
     * @param oldDefaultCompanyId
     * @return
     */
    Boolean changeDefaultCompany(Integer defaultCompanyId, Integer oldDefaultCompanyId);

}
