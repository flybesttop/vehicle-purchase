package com.vp.service;

import com.vp.entity.Company;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Service
public interface CompanyService {
    /**
     * 创建企业，组织
     * @param company
     * @return
     */
    boolean createCompany(Company company);
}
