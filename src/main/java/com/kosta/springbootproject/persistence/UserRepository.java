package com.kosta.springbootproject.persistence;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kosta.springbootproject.model.QUser;
import com.kosta.springbootproject.model.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface UserRepository extends CrudRepository<User, Long>,QuerydslPredicateExecutor<User>{
	
	
	//사용자 조건 조회 메서드
	public default Predicate makePredicate(String type, String keyword) {
		BooleanBuilder builder = new BooleanBuilder();
		QUser user = QUser.user;
		builder.and(user.userNo.gt(0)); //and userNo>0
		if(type==null)return builder;
		switch (type) {
		case "Name":
			builder.and(user.userName.like("%"+keyword+"%")); //and title like '%?%'
			break;
		case "Id":
			builder.and(user.userId.like("%"+keyword+"%")); //and title like '%?%'
			break;
		case "Phone":
			builder.and(user.userPhone.like("%"+keyword+"%")); //and title like '%?%'
			break;
		case "Email":
			builder.and(user.userEmail.like("%"+keyword+"%")); //and title like '%?%'
			break;
		case "Company":
			builder.and(user.company.companyName.like("%"+keyword+"%")); //and title like '%?%'
			break;
		default:
			break;
		}
		return builder;
	}
}
