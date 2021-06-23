package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.model.Trainee;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.TraineeRepository;
import com.kosta.springbootproject.persistence.UserRepository;
import com.querydsl.core.types.Predicate;

@Service
public class UserService {

	@Autowired
	UserRepository userrepo;
	@Autowired
	TraineeRepository traineerepo;
	
	public List<Users> selectAll(){
		return (List<Users>) userrepo.findAll();
	}
	
	public Page<Users> selectAll(PageVO pvo) {
		Predicate p = userrepo.makePredicate(pvo.getType(),pvo.getKeyword());
		
		Pageable pageable = pvo.makePaging(0, "userNo");
		Page<Users> result = userrepo.findAll(p, pageable);
		return result;
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
