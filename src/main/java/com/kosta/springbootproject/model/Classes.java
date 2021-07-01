package com.kosta.springbootproject.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.querydsl.core.annotations.QueryInit;

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
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long classNo;

	@Column(nullable = false)
	private Date classOpenDate;

	@Column(nullable = false)
	private Date classCloseDate;

	@Column(length = 1000)
	private String classDescription;

	@ColumnDefault("false")
	// @Column(columnDefinition = "boolean default false")
	private Boolean classRecommend;

	@Enumerated(EnumType.STRING)
	private ClassStateEnumType classState;

	@Column
	private Integer waitCount;
	@Column
	private Integer cancelCount;
	@Column
	private Integer commitCount;
	
	//Querydsl은 처음 2레벨의 깊이까지만 초기화한다.
	//더 깊은 경로로 초기화 해야한다면 @QueryInit으로 초기화 시켜줘야한다.
	@ManyToOne
	@QueryInit("course.*")
	private Lecture lecture;
	@ManyToOne
	private Teacher teacher;
	
	//Querydsl은 처음 2레벨의 깊이까지만 초기화한다.
	//더 깊은 경로로 초기화 해야한다면 @QueryInit으로 초기화 시켜줘야한다.
	@ManyToOne
	@QueryInit("lectureHall.*")
	private ClassRoom classRoom;
	@ManyToOne
	private EducationTime educationTime;
	@ManyToOne
	private Admin admin;

	@PrePersist
	public void prePersist() {
		this.classRecommend = this.classRecommend == null ? false : this.classRecommend;
		this.waitCount = 0;
		this.cancelCount = 0;
		this.commitCount = 0;
		this.classState = ClassStateEnumType.OPENREADY;
	}

}
