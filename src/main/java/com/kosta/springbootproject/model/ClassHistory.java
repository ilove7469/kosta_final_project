package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ClassHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long classHistoryNo;
	
	@Enumerated(EnumType.STRING)
	private ClassHistoryEnumType classHistoryState;
	
	@CreatedDate
	private Date classHistoryDate;
	
	//양방향
	@ManyToOne
	private User userHistory;
	
	//양방향
	@ManyToOne
	private Classes classes;
}
