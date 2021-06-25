package com.kosta.springbootproject.userservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class UserPageUserService {

	@Autowired
	MemberReposiroty mrepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	CompanyRepository crepo;
	
	public Optional<MemberDTO> findMemberIdByUserId(String userId){
		return mrepo.findById(userId); 
	}
	
	public void insertUser(Users user) {
		urepo.save(user);
	}
	
	//"/user/userInfo" 유저조회
	public Users findUserByUserId(String userId) { 
		Users userInfo = urepo.findByUserId(userId); 
		if(userInfo.getCompany()==null) {
			Company company = Company.builder()
					.companyNo(0L)
					.companyName(" ")
					.build();
			userInfo.setCompany(company);
		}
		return userInfo;
	}
	//회사전체조회
	public List<Company> findCompanyAll(){
		return (List<Company>)crepo.findAll();
	}
}
