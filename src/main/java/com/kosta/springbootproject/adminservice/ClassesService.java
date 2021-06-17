package com.kosta.springbootproject.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.ClassesRepository;
import com.querydsl.core.types.Predicate;

@Service
public class ClassesService {

	@Autowired
	ClassesRepository classesRepo;
	
	public Page<Classes> selectAll(PageVO pvo) { 
		Predicate p = classesRepo.makePredicateClasses(pvo.getType(),pvo.getKeyword()); 
	
		Pageable pageable = pvo.makePaging(0, "classNo");
		
		Page<Classes> result = classesRepo.findAll(p, pageable);
		return result;
		 
	}
	
//강의추가
	public Classes updateOrInsert(Classes classes) {
		return classesRepo.save(classes);
	}


		public int deleteClasses(Long cno) {
			
			int ret=0;
			
			try {
				classesRepo.deleteById(cno);
			ret=1;
			}catch(Exception ex) {
			
			}
			return ret;
		}
}
