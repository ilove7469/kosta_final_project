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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int certiNo;
	@Column(nullable = false)
	String certiName;
	@Column(length = 1000)
	String certiComment;
	@CreatedDate
	Date certiRegDate;
	
}
