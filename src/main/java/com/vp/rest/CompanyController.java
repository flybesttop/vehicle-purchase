package com.vp.rest;

import com.vp.entity.Company;
import com.vp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author flybesttop
 * @date 2020/11/26
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Autowired
    public CompanyService companyService;

    @RequestMapping(value = "createCompany",method = POST)
    public boolean createCompany(HttpServletRequest request, @RequestBody Company company){
        return companyService.createCompany(company);
    }
}
