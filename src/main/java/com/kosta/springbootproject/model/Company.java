package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long companyNo;
	
	@Column(nullable = false)
	private String companyName;
	
	@Column(unique = true)
	private String companyLicense;
	
	@Column(nullable = false)
	private String companyBoss;
	
	@Enumerated(EnumType.STRING)
	private ConventionEnumType companyConvention;

}
