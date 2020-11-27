package com.vp.service;

import com.vp.entity.Company;
import org.springframework.stereotype.Service;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Service
public interface CompanyService {
    boolean createCompany(Company company);
}
