package com.devendrabrain.jpa.batchsize.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devendrabrain.jpa.batchsize.dao.UserCompanyDAO;
import com.devendrabrain.jpa.batchsize.entity.UserCompany;
import com.devendrabrain.jpa.batchsize.entity.UserCompanyAddress;

@Service
public class UserCompanyService {

	@Autowired
	UserCompanyDAO companyDAO;

	public List<UserCompany> create(List<UserCompany> userCompany) {
		
		List<UserCompany> companies = new ArrayList<UserCompany>();		
		for (UserCompany userCompanyobj : userCompany) {
			companies.add(companyDAO.create(userCompanyobj));
		}
		return companies;
	}

	public List<UserCompanyAddress> findAll() {
		List<UserCompany> usercompany = companyDAO.findAll();
		List<UserCompanyAddress> usercompanyAddress = new ArrayList<UserCompanyAddress>();
		
		/**
		 * 
		 * Because we set BatchSize to 3, Hibernate query in where clause used 
		 *  usercompan0_.user_company_id in (
            ?, ?, ?
            )
		 * Only making 1 call for 3 id insted of making 3 seperate call
		 * 
		 * Hibernate: 
    select
        usercompan0_.user_company_id as user_com3_1_1_,
        usercompan0_.id as id1_1_1_,
        usercompan0_.id as id1_1_0_,
        usercompan0_.company_address_id as company_2_1_0_,
        usercompan0_.user_company_id as user_com3_1_0_ 
    from
        user_company_address usercompan0_ 
    where
        usercompan0_.user_company_id in (
            ?, ?, ?
        )
Hibernate: 
    select
        usercompan0_.user_company_id as user_com3_1_1_,
        usercompan0_.id as id1_1_1_,
        usercompan0_.id as id1_1_0_,
        usercompan0_.company_address_id as company_2_1_0_,
        usercompan0_.user_company_id as user_com3_1_0_ 
    from
        user_company_address usercompan0_ 
    where
        usercompan0_.user_company_id in (
            ?, ?, ?
        )
		 * 
		 * 
		 */
		
		
		for (UserCompany userCompanyObj : usercompany) {
			usercompanyAddress.addAll(userCompanyObj.getUserCompanyAddress());
		}
		
		return usercompanyAddress;
	}
}
