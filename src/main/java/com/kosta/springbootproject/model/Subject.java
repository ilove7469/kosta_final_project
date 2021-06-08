package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long subjectNo;
	private int subPriority;
	@Column(nullable = false)
	private String subName;
	
	@Column(length = 1000)
	private String subExplain;
	
	@ManyToOne
	private Trainee trainee;
	
}
