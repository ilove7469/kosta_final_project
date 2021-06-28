package com.kosta.springbootproject.userservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.persistence.TraineeRepository;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class UserPageUserService {

	@Autowired
	MemberReposiroty mrepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	CompanyRepository crepo;
	@Autowired
	TraineeRepository trepo;
	@Autowired
	ClassHistoryRepository chrepo;
	
	public Optional<MemberDTO> findMemberIdByUserId(String userId){
		return mrepo.findById(userId); 
	}
	//유저 등록
	public void insertUser(Users user) {
		String traineeName = "채용예정자";
		Trainee trainee = trepo.findBytraineeName(traineeName);
		user.setTrainee(trainee);
		urepo.save(user);
	}
	
	//유저 업데이트
	public void updateUserAndCompany(Users user,Long companyNo) {
		if(companyNo!=0) {
			Company company = crepo.findById(companyNo).get();
			user.setCompany(company);
		}
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
	
	//재직자 채용예정자 전환
	public Users findUsersByUsersNo(Long usersNo){
		Users users = urepo.findById(usersNo).get();
		return users;
	}
	public void changeToUnemp(Long usersNo){
		Users users = urepo.findById(usersNo).get();
		Trainee trainee = trepo.findBytraineeName("채용예정자");
		users.setTrainee(trainee);
		users.setCompany(null);
		users.setDeptPhone(null);
		users.setUserDept(null);
		users.setUserPosition(null);
		users.setUserJob(null);
		urepo.save(users);
	}
	
	public void changeToEmp(Long usersNo){
		Users users = urepo.findById(usersNo).get();
		Trainee trainee = trepo.findBytraineeName("재직자");
		users.setTrainee(trainee);
		urepo.save(users);
	}
	
	//유저삭제
	public void deleteUser(Long userNo) {
		urepo.deleteById(userNo);
	}
	
	//유저번호로 classhistory 찾기
	public List<ClassHistory> findClassHistoryByUser(Long userNo){
		Users user = Users.builder()
				.userNo(userNo)
				.build();
		return chrepo.findByUser(user);
	}
	
	//유저프로필 카운트 조회
	public List<Object[]> selectClassHistoryCountByUser(Long userNo){
		List<Object[]> classHistoryCount = chrepo.findClassHistoryCountByUser(userNo);
		return classHistoryCount;
	}
	
}
