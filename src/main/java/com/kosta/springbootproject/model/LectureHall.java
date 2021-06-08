package com.kosta.springbootproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class LectureHall {

	@Id
	private int lectureHallNo;
	
	@Column(nullable = true)
	private String lectureHallName;
	
	@Column(nullable = true)
	private String lectureHallAddress;
	
	@Column(nullable = true)
	private String lectureHallPhone;

}
