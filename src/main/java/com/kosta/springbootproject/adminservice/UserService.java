package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.TraineeRepository;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	@Autowired
	TraineeRepository traineerepo;
	
	public List<Users> selectAll(){
		 List<Users> resultlist = (List<Users>) userrepo.findAll();
		 for(Users result:resultlist) {
			 if(result.getCompany()==null){
				 Company company = Company
						 .builder()
						 .companyName(" ")
						 .build();
				 result.setCompany(company);
			 }
		 }
		 return resultlist;
	}
	
	
	public Users findUsersByUsersNo(Long usersNo){
		Users users = userrepo.findById(usersNo).get();
		return users;
	}
	
	public void changeToUnemp(Long usersNo){
		Users users = userrepo.findById(usersNo).get();
		Trainee trainee = traineerepo.findBytraineeName("채용예정자");
		users.setTrainee(trainee);
		userrepo.save(users);
	}
	
	public void changeToEmp(Long usersNo){
		Users users = userrepo.findById(usersNo).get();
		Trainee trainee = traineerepo.findBytraineeName("재직자");
		users.setTrainee(trainee);
		userrepo.save(users);
	}
}
