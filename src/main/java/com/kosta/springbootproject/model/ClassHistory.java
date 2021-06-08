package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private int classHistoryNo;
	
	@Enumerated(EnumType.STRING)
	private ClassHistoryEnumType classHistoryState;
	
	private Date classHistoryDate;
	
	//양방향
	@ManyToOne
	private User userHistoryNo;
	
	//양방향
	@ManyToOne
	private Classes classNo;
}
