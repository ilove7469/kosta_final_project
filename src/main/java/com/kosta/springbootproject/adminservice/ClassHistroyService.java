package com.kosta.springbootproject.adminservice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassHistoryEnumType;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;
import com.kosta.springbootproject.persistence.ClassesRepository;

@Service
public class ClassHistroyService {
	
	@Autowired
	ClassHistoryRepository classHistoryRepo;
	
	@Autowired
	ClassesRepository classesRepo;
	
	public List<ClassHistory> selectAll(){
		return (List<ClassHistory>) classHistoryRepo.findAll();
	}

	public ClassHistory findClassHistoryByNo(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		return classHistory;
	}
	
//  신청상태를 Commit으로 변경
	public Long commitManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		Classes classes = classesRepo.findById(classHistory.getClasses().getClassNo()).get();
		if(classHistory.getClassHistoryState().toString()=="CANCEL") {
			classes.setCancelCount(classes.getCancelCount()-1);
			classes.setCommitCount(classes.getCommitCount()+1);
		} 
		if(classHistory.getClassHistoryState().toString()=="WAIT") {
			classes.setWaitCount(classes.getWaitCount()-1);
			classes.setCommitCount(classes.getCommitCount()+1);
		} 
		classHistory.setClassHistoryState(ClassHistoryEnumType.COMMIT);
		classHistoryRepo.save(classHistory);
		classesRepo.save(classes);
		return classHistory.getClasses().getClassNo();
	}
	
//  신청상태를 Cancel으로 변경
	public Long cancelManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		Classes classes = classesRepo.findById(classHistory.getClasses().getClassNo()).get();
		if(classHistory.getClassHistoryState().toString()=="COMMIT") {
			classes.setCommitCount(classes.getCommitCount()-1);
			classes.setCancelCount(classes.getCancelCount()+1);
		} 
		if(classHistory.getClassHistoryState().toString()=="WAIT") {
			classes.setWaitCount(classes.getWaitCount()-1);
			classes.setCancelCount(classes.getCancelCount()+1);
		} 
		classHistory.setClassHistoryState(ClassHistoryEnumType.CANCEL);
		classHistoryRepo.save(classHistory);
		classesRepo.save(classes);
		return classHistory.getClasses().getClassNo();
	}
	
//  신청상태를 Wait으로 변경
	public Long waitManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		Classes classes = classesRepo.findById(classHistory.getClasses().getClassNo()).get();
		if(classHistory.getClassHistoryState().toString()=="CANCEL") {
			classes.setCancelCount(classes.getCancelCount()-1);
			classes.setWaitCount(classes.getWaitCount()+1);
		} 
		if(classHistory.getClassHistoryState().toString()=="COMMIT") {
			classes.setCommitCount(classes.getCommitCount()-1);
			classes.setWaitCount(classes.getWaitCount()+1);
		} 
		classHistory.setClassHistoryState(ClassHistoryEnumType.WAIT);
		classHistoryRepo.save(classHistory);
		classesRepo.save(classes);
		return classHistory.getClasses().getClassNo();
	}
	
//  신청상태를 Uncompleted으로 변경
	public Long uncompleteManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		Classes classes = classesRepo.findById(classHistory.getClasses().getClassNo()).get();
		classHistory.setClassHistoryState(ClassHistoryEnumType.UNCOMPLETED);
		classHistoryRepo.save(classHistory);
		classesRepo.save(classes);
		return classHistory.getClasses().getClassNo();
	}
	
//  신청상태를 Completed으로 변경
	public Long completeManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = classHistoryRepo.findById(classHistoryNo).get();
		Classes classes = classesRepo.findById(classHistory.getClasses().getClassNo()).get();
		classHistory.setClassHistoryState(ClassHistoryEnumType.COMPLETED);
		classHistoryRepo.save(classHistory);
		classesRepo.save(classes);
		return classHistory.getClasses().getClassNo();
	}
	
//  classes의 closedate날짜가 지나면 모든 commit을 completed로 변환
	public void commitToComplete() {
		List<ClassHistory> classHistroyList = (List<ClassHistory>)classHistoryRepo.findAll();
		for(ClassHistory classHistory : classHistroyList) {
			if(classHistory.getClasses().getClassCloseDate().compareTo(new Date())<0 && classHistory.getClassHistoryState().equals(ClassHistoryEnumType.COMMIT)) {
				classHistory.setClassHistoryState(ClassHistoryEnumType.COMPLETED);
				classHistoryRepo.save(classHistory);
			}
		}
	}

}
