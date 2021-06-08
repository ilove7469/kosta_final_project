package com.kosta.springbootproject.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString//(exclude = {"companyNo", "traineeNo"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userNo;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false, unique = true)
	private String userId;
	
	@Column(nullable = false)
	private String userPw;
	
	@Column(nullable = false)
	private String userPhone;
	
	@Column(nullable = false)
	private String userEmail;
	
	@Column(nullable = false)
	private String userAddress;
	
	@Column(nullable = false)
	private Date userBirth;
	
	private String userBank;
	private String userBankNoString;
	private String userIdentity;
	
	@CreatedDate
	private Date userRegDate;
	private String userDept;
	private String userPosition;
	private String userJob;
	private String deptPhone;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Trainee trainee;
	
	
	/*
	@OneToMany(mappedBy = "userHistoryNo", cascade = CascadeType.ALL)
	List<ClassHistory> userHistory;
	*/
	
}
