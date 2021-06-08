package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long teacherNo ;
	
	@Column(unique = true, nullable = false)
	private String teacherId;
	
	@Column(nullable = false)
	private String teacherName;
	
	@Column(nullable = false)
	private String teacherPhone;
	
	@Column(nullable = false)
	private String teacherEmail;
	
	@CreatedDate
	private Date teacherRegDate;
	
}
