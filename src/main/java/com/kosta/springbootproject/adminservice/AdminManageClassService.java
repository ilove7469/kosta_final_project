package com.kosta.springbootproject.adminservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassStateEnumType;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.ConventionEnumType;
import com.kosta.springbootproject.model.Users;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;

@Service
public class AdminManageClassService {
	
	@Autowired
	ClassesRepository classrepo;
	@Autowired
	ClassHistoryRepository classhistoryrepo;
	
	public List<Classes> selectAll(){
		List<Classes> classList = (List<Classes>) classrepo.findAll();
		for(Classes classes:classList) {
			Integer capa= classes.getLecture().getCourse().getCourseCapacity();
			Date Today = new Date();
			// 강의상태 = APPLY(OPENREADY때문에 넣어주었다.) && 목표정원 <= 확정인원 && 개강일 > 오늘날짜
 			if(classes.getClassState()==ClassStateEnumType.APPLY
 			   &&capa <= classes.getCommitCount()
 			   &&classes.getClassOpenDate().compareTo(Today)>0) {
					classes.setClassState(ClassStateEnumType.END);
					classrepo.save(classes);
			} else if(classes.getClassState()==ClassStateEnumType.END
		 			   &&capa > classes.getCommitCount()
		 			   &&classes.getClassOpenDate().compareTo(Today)>0){
					classes.setClassState(ClassStateEnumType.APPLY);
					classrepo.save(classes);
			}
		}
		return classList;
		
	}
	
	public List<ClassHistory> findClassHistoryByClasses(Long classNo){
		Classes classes = Classes.builder()
				.classNo(classNo)
				.build();
		List<ClassHistory> HistoryList = classhistoryrepo.findByClasses(classes);
		for(ClassHistory history:HistoryList) {
			 if(history.getUser().getCompany()==null){
				 Company company = Company
						 .builder()
						 .companyName(" ")
						 .companyConvention(ConventionEnumType.None)
						 .build();
				 history.getUser().setCompany(company);
			 }
			 if(history.getUser().getUserJob() == null) {
				 history.getUser().setUserJob(" ");
			 }
			 if(history.getUser().getUserPosition() == null) {
				 history.getUser().setUserPosition(" ");
			 }
			 if(history.getUser().getUserIdentity() == null) {
				 history.getUser().setUserIdentity(" ");
			 }
		 }
		return HistoryList;
	}
	
	public List<ClassHistory> findClassHistoryByUser(Long userNo){
		Users user = Users.builder()
				.userNo(userNo)
				.build();
		List<ClassHistory> HistoryList = classhistoryrepo.findByUser(user);
		return HistoryList;
	}
	
	public List<Object[]> selectClassHistoryCountByUser(Long userNo){
		List<Object[]> classHistoryCount = classhistoryrepo.findClassHistoryCountByUser(userNo);
		return classHistoryCount;
	}
	
}
