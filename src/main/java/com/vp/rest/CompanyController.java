package com.vp.rest;

import com.vp.entity.Company;
import com.vp.service.CompanyService;
import com.vp.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@Slf4j
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    public CompanyService companyService;

    @RequestMapping(value = "createCompany", method = POST)
    public BaseResponse<Boolean> createCompany(@RequestBody Company company) {
        return new BaseResponse<>(companyService.createCompany(company));
    }
}
