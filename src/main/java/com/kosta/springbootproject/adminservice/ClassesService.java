package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kosta.springbootproject.model.Classes;
import com.kosta.springbootproject.model.Company;
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

	
	//엑셀다운로드
		 public XSSFWorkbook listExcelDownload(Company param, Model model,PageVO pvo) throws Exception {
		        
			 //System.out.println("--------------------엑셀확인------------------");
			 //System.out.println(pvo.getKeyword() +"----------------"+pvo.getType());
			 
			 XSSFWorkbook workbook = new XSSFWorkbook();
		        
			 XSSFSheet sheet = workbook.createSheet("엑셀시트명");
		        
			 XSSFRow row = null;
		        
		     XSSFCell cell = null;
	   
		     //param.setPager(false);
		     //param.setNullText(NULL_TEXT);
		     //param.setSeparator(DELI_EXCEL);
		     Predicate p = classesRepo.makePredicateClasses(pvo.getType(),pvo.getKeyword()); 
		     //System.out.println("동적조회 p :"+p);
		        
		     List<Classes> list = (List<Classes>) classesRepo.findAll(p);
		        
		     //System.out.println(list);
		        
		     row = sheet.createRow(0);
		     String[] headerKey = {"주제명", "강의명", "강사명", "개강", "종강", "강의장명", "상태"};
		        
		        for(int i=0; i<headerKey.length; i++) {
		            cell = row.createCell(i);
		            cell.setCellValue(headerKey[i]);
		        }
		        
		        for(int i=0; i<list.size(); i++) {
		            row = sheet.createRow(i + 1);
		            Classes vo = list.get(i);
		           
		            System.out.println("-----엑셀겨롹"+vo);
		            cell = row.createCell(0);
		            //cell.setCellValue(vo.);
		            
		            cell = row.createCell(1);
		           // cell.setCellValue(vo.getCompanyLicense());
		            
		            cell = row.createCell(2);
		           // cell.setCellValue(vo.getCompanyBoss());
		            
		            cell = row.createCell(3);
		            cell.setCellValue(vo.getClassOpenDate().toString());
		            
		            cell = row.createCell(4);
		            cell.setCellValue(vo.getClassCloseDate().toString());
		            
		            cell = row.createCell(5);
		         //   cell.setCellValue(vo.getCompanyConvention().toString());
		            
		            cell = row.createCell(6);
		         //   cell.setCellValue(vo.getCompanyConvention().toString());

		        }
		        
		        return workbook;
		    }
		 //엑셀다운로드
		 
}
