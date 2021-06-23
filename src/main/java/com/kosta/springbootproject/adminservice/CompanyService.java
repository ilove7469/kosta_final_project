package com.kosta.springbootproject.adminservice;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.kosta.springbootproject.model.Company;
import com.kosta.springbootproject.model.Course;
import com.kosta.springbootproject.model.PageVO;
import com.kosta.springbootproject.persistence.CompanyRepository;
import com.querydsl.core.types.Predicate;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyrepo;
	
	
	public Page<Company> selectAll(PageVO pvo) { 
		Predicate p = companyrepo.makePredicate(pvo.getType(),pvo.getKeyword()); 
	
		Pageable pageable = pvo.makePaging(0, "companyNo");
		
		Page<Company> result = companyrepo.findAll(p, pageable);
		return result;
		 
	}
	
	
	public Company insertCompany(Company company) {
		return companyrepo.save(company);
	}
	
	public Company selectById(Long companyNo) {
		return companyrepo.findById(companyNo).get();
	}
	
	public int deleteCompany(Long cno) {
		
		int ret=0;
		
		try {
		companyrepo.deleteById(cno);
		ret=1;
		}catch(Exception ex) {
		
		}
		return ret;
	}


	public Company updateCourse(Company company) {
		return companyrepo.save(company);
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
	     Predicate p = companyrepo.makePredicate(pvo.getType(),pvo.getKeyword()); 
	     //System.out.println("동적조회 p :"+p);
	        
	     List<Company> list = (List<Company>) companyrepo.findAll(p);
	        
	     //System.out.println(list);
	        
	     row = sheet.createRow(0);
	     String[] headerKey = {"회사명", "사업자번호", "대표", "협약사"};
	        
	        for(int i=0; i<headerKey.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellValue(headerKey[i]);
	        }
	        
	        for(int i=0; i<list.size(); i++) {
	            row = sheet.createRow(i + 1);
	            Company vo = list.get(i);
	           
	            cell = row.createCell(0);
	            cell.setCellValue(vo.getCompanyName());
	            
	            cell = row.createCell(1);
	            cell.setCellValue(vo.getCompanyLicense());
	            
	            cell = row.createCell(2);
	            cell.setCellValue(vo.getCompanyBoss());
	            
	            cell = row.createCell(3);
	            cell.setCellValue(vo.getCompanyConvention().toString());

	        }
	        
	        return workbook;
	    }
	 //엑셀다운로드
}
