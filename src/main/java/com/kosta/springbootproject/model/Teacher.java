package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private int teacherNo ;
	
	@Column(unique = true)
	private int teacherId;
	
	@Column(nullable = true)
	private String teacherName;
	
	@Column(nullable = true)
	private String teacherPhone;
	
	@Column(nullable = true)
	private String teacherEmail;
	
	@CreatedDate
	private Date teacherRegDate;
	
}
