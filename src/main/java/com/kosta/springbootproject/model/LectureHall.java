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
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
public class LectureHall implements Comparable<LectureHall>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lectureHallNo;
	
	@Column(nullable = false)
	private String lectureHallName;
	
	@Column(nullable = false)
	private String lectureHallAddress;
	
	@Column(nullable = false)
	private String lectureHallPhone;

	//정렬을 위해 override
	@Override
	public int compareTo(LectureHall o) {
		return Long.compare(this.lectureHallNo, o.lectureHallNo);
	}

}
