package com.kosta.springbootproject.model;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Users {
	
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
	private char sex;
	
	@Column(nullable = false)
	private String userPhone;
	
	@Column(nullable = false)
	private String userEmail;
	
	@Column(nullable = false)
	private String zipCode;
	
	@Column(nullable = false)
	private String userAddress;
	
	@Column(nullable = false)
	private String detailAddress;
	
	@Column(nullable = false)
	private Date userBirth;
	
	private String userBank;
	private String userBankNoString;
	private String userIdentity;
	
	@CreationTimestamp
	private Timestamp userRegDate;
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
