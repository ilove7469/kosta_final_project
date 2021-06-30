package com.kosta.springbootproject.adminservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.ClassStateEnumType;
import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.persistence.ClassesRepository;

@Service
public class ClassesService {

	@Autowired
	ClassesRepository classesRepo;
	
	public Classes selectById(Long classNo) {
		return classesRepo.findById(classNo).get();
	}

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

	public Classes updateClasses(Classes classes) {
		return classesRepo.save(classes);
	}
	
	public List<Classes> selectAll(){
		List<Classes> classesList = (List<Classes>)classesRepo.findAllByOrderByClassNoDesc();
		return classesList;
	}

	
	public XSSFWorkbook listExcelDownload(List<Classes> list) throws Exception {
			
		XSSFWorkbook workbook = new XSSFWorkbook();  
		XSSFSheet sheet = workbook.createSheet("kosta"); 
		XSSFRow row = null;
		XSSFCell cell = null;
		
		row = sheet.createRow(0);
		String[] headerKey = {"주제명", "강의명", "강사명", "개강", "종강", "강의장명", "상태"}; //엑셀 칼럼명
		
		for(int i=0; i<headerKey.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headerKey[i]);
		}
		
		for(int i=0; i<list.size(); i++) {
			row = sheet.createRow(i + 1);
			Classes vo = list.get(i);
			
			cell = row.createCell(0);
			cell.setCellValue(vo.getLecture().getCourse().getSubject().getSubName());
			cell = row.createCell(1);
			cell.setCellValue(vo.getLecture().getCourse().getCourseName());
			cell = row.createCell(2);
			cell.setCellValue(vo.getTeacher().getTeacherName());
			cell = row.createCell(3);
			cell.setCellValue(vo.getClassOpenDate().toString());
			cell = row.createCell(4);
			cell.setCellValue(vo.getClassCloseDate().toString());
			cell = row.createCell(5);
			cell.setCellValue(vo.getClassRoom().getLectureHall().getLectureHallName());
			cell = row.createCell(6);
			cell.setCellValue(vo.getClassState().toString());
		}
		return workbook;
	}

	
//  Scheduler에서 classState를 바꿔주기 위한 메서드
	public void manageClassState() {
		List<Classes> classesList = (List<Classes>)classesRepo.findAll();
		for(Classes classes:classesList) {
			Calendar cal = Calendar.getInstance();
			Date Today = new Date();
			cal.setTime(Today);
			cal.add(Calendar.DATE, 60);
			
			// 개강일 - (현재날짜+60일) <= 0 [60일도 안남았다면]
			if(classes.getClassOpenDate().compareTo(cal.getTime())<=0) {
				classes.setClassState(ClassStateEnumType.APPLY);
				classesRepo.save(classes);
			}
			
			// 개강일 - 현재날짜 <= 0 [마감]
			if(classes.getClassOpenDate().compareTo(Today)<=0){
				classes.setClassState(ClassStateEnumType.END);
				classesRepo.save(classes);
			}
		}
	}

//관리자 메인페이지 - 최근 개강예정 강의출력
	public List<Classes> selectRecentOpenClasses() {
		//줄 수 
		Integer rownum = 5;
		// ClassesList를 "개강일을 기준"으로 오름차순 정렬
		List<Classes> classesList = (List<Classes>)classesRepo.findAllByOrderByClassOpenDateAsc();
		List<Classes> selectList = new ArrayList<>();
		Date Today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(Today);
		//오늘 날짜에서 30일을 더한다.
		cal.add(Calendar.DATE, 30);
		for(Classes classes : classesList) {
			//오늘 이후 && 오늘로부터 30일 이내 개강예정인 강의
			if(classes.getClassOpenDate().compareTo(Today)>=0 && classes.getClassOpenDate().compareTo(cal.getTime())<=0) {
				if(selectList.size()>=rownum) break;
				selectList.add(classes);
			}
		}
		return selectList;
	}
	
	//관리자 메인페이지 - 최근 종강예정 강의출력	
	public List<Classes> selectRecentCloseClasses() {
		//줄 수 5줄만 출력
		Integer rownum = 5;
		// ClassesList를 "종강일을 기준"으로 오름차순 정렬
		List<Classes> classesList = (List<Classes>)classesRepo.findAllByOrderByClassCloseDateAsc();
		List<Classes> selectList = new ArrayList<>();
		Date Today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(Today);
		//오늘 날짜에서 30일을 더한다.
		cal.add(Calendar.DATE, 30);
		for(Classes classes : classesList) {
			//오늘 이후이면서 && 오늘로부터 30일 이내 개강예정인 강의
			if(classes.getClassCloseDate().compareTo(Today)>=0 && classes.getClassCloseDate().compareTo(cal.getTime())<=0) {
				if(selectList.size()>=rownum) break;
				selectList.add(classes);
			}
		}
		return selectList;
	}
}
