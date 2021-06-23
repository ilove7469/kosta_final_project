package com.kosta.springbootproject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kosta.springbootproject.model.Admin;
import com.kosta.springbootproject.model.MemberDTO;
import com.kosta.springbootproject.model.MemberRoleEnumType;
import com.kosta.springbootproject.model.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";
    private MemberDTO member;   
	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public SecurityUser(MemberDTO member) {	
		super(member.getMid(), member.getMpassword(), makeRole(member)  );
		this.member = member;
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(MemberDTO member) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + member.getMrole()));
		return roleList;
	}

}
