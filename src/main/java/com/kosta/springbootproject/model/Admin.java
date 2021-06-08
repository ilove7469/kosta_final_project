package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminNo;
	
	@Column(nullable = false)
	private String adminName;
	
	@Column(nullable = false, unique = true)
	private String adminId;
	
	@Column(nullable = false)
	private String adminPw;
	
	@Column(nullable = false)
	private String adminPhone;
	
	@Column(nullable = false)
	private String adminEmail;
}
