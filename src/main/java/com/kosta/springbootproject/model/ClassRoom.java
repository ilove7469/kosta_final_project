package com.kosta.springbootproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class ClassRoom {

	@Id
	private int classRoomNo;
	
	@Column(nullable = true)
	private int lectureHallNo;
	
	private int classRoomCapacity;
	
	@ManyToOne 
	LectureHall lecturehall;
}
