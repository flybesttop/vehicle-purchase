package com.vp.service.impl;

import com.vp.dao.CompanyMapper;
import com.vp.entity.Company;
import com.vp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    public CompanyMapper companyMapper;

    @Override
    public boolean createCompany(Company company) {
        int sameNameCompany = companyMapper.selectSameNameCompany(company);
        if (sameNameCompany > 0){
            //当前客户已创建同名的公司了
            return false;
        }
        int result = companyMapper.insert(company);
        return result != 0;
    }
}


