package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.persistence.ClassRoomRepository;

@Service
public class ClassRoomService {
	
	@Autowired
	ClassRoomRepository repo;
	
	public List<ClassRoom> selectAll(){
		return (List<ClassRoom>) repo.findAll();
	}

	public ClassRoom updateOrInsert(ClassRoom classRoom) {
		return repo.save(classRoom);
	}
	
	public int deleteClassRoom(Long no) {
		
		int result=0;
		
		try {
		repo.deleteById(no);
		result=1;
		}catch(Exception ex) {
		
		}
		return result;
	}
	
	public ClassRoom findClassRoomByNo(Long classRoomNo){
		ClassRoom classRoom = repo.findById(classRoomNo).get();
		return classRoom;
	}
}
