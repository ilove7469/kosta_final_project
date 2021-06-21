package com.kosta.springbootproject.adminservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.springbootproject.model.Certificate;
import com.kosta.springbootproject.model.ClassRoom;
import com.kosta.springbootproject.persistence.CertificateRepository;

@Service
public class CertificateService {
	
	@Autowired
	CertificateRepository repo;
	
	public List<Certificate> selectAll(){
		return (List<Certificate>) repo.findAll();
	}
	
	public Certificate updateOrInsert(Certificate certi) {
		return repo.save(certi);
	}
	
	public Certificate selectById(Long certificate_certi_no) {
		return repo.findById(certificate_certi_no).get();
	}
	
	public int deleteCertificate(Long no) {
		
		int result=0;
		
		try {
		repo.deleteById(no);
		result=1;
		}catch(Exception ex) {
		
		}
		return result;
	}
	
	public Certificate findCertificateByNo(Long certiNo){
		Certificate certificate = repo.findById(certiNo).get();
		return certificate;
	}
}
