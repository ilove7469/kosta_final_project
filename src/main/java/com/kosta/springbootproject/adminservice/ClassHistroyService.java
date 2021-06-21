package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassHistory;
import com.kosta.springbootproject.model.ClassHistoryEnumType;
import com.kosta.springbootproject.persistence.ClassHistoryRepository;

@Service
public class ClassHistroyService {
	
	@Autowired
	ClassHistoryRepository repo;
	
	public List<ClassHistory> selectAll(){
		return (List<ClassHistory>) repo.findAll();
	}

// 신청상태를 Commit으로 변경
	public void commitManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = repo.findById(classHistoryNo).get();
		classHistory.setClassHistoryState(ClassHistoryEnumType.COMMIT);
		repo.save(classHistory);
	}
// 신청상태를 Cancel으로 변경
	public void cancelManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = repo.findById(classHistoryNo).get();
		classHistory.setClassHistoryState(ClassHistoryEnumType.CANCEL);
		repo.save(classHistory);
	}
// 신청상태를 Wait으로 변경
	public void waitManageClassDetail(Long classHistoryNo) {
		ClassHistory classHistory = repo.findById(classHistoryNo).get();
		classHistory.setClassHistoryState(ClassHistoryEnumType.WAIT);
		repo.save(classHistory);
	}

}
