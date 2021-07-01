package com.kosta.springbootproject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.AdminRepository;
import com.kosta.springbootproject.persistence.MemberReposiroty;
import com.kosta.springbootproject.persistence.UserRepository;

@Service
public class MemberService implements UserDetailsService{

	@Autowired
	MemberReposiroty repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	public MemberDTO joinUser(MemberDTO member) {
		member.setMpassword(passwordEncoder.encode(member.getMpassword()));
		return repo.save(member);
	}
	
	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
		UserDetails member = repo.findById(mid)
				.filter(m -> m != null).map(m -> new SecurityUser(m)).get();
		return member;
	}
	//맴버 업데이트
	public void updateMember(Users user) {
		repo.findById(user.getUserId()).ifPresent(member->{
			String newUserName = user.getUserName();
			if(!(member.getMname().equals(newUserName))){
				member.setMname(user.getUserName());
				repo.save(member);
			}
		});
	}
	//맴버 삭제
	public void deleteMember(String mid) {
		repo.deleteById(mid);
	}
}
