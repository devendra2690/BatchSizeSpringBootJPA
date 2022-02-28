package com.devendrabrain.jpa.batchsize.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class UserCompanyAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyAddressId;

	@ManyToOne
	@JoinColumn(name = "user_company_id")
    @JsonBackReference
	private UserCompany userCompany;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyAddressId() {
		return companyAddressId;
	}

	public void setCompanyAddressId(String companyAddressId) {
		this.companyAddressId = companyAddressId;
	}

	public UserCompany getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(UserCompany userCompany) {
		this.userCompany = userCompany;
	}

}
