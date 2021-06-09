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
	
	@Column(length = 1000)
	private String classDescription;
	
	@ColumnDefault("false")
	//@Column(columnDefinition = "boolean default false")
	private Boolean classRecommend;
	
	@Enumerated(EnumType.STRING)
	private ClassStateEnumType classState;
	
	@ManyToOne
	private Lecture lecture;
	@ManyToOne
	private Teacher teacher;
	@ManyToOne
	private ClassRoom classRoom;
	@ManyToOne
	private EducationTime educationTime;
	@ManyToOne
	private Admin admin;
	
	@PrePersist
    public void prePersist() {
        this.classRecommend = this.classRecommend == null ? false : this.classRecommend;
    }
	
}
