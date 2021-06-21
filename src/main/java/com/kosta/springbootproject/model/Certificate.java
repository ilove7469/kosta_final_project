package com.kosta.springbootproject.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class Certificate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long certiNo;
	
	@Column(nullable = false)
	private String certiName;
	
	@Column(length = 1000)
	private String certiComment;
	
	@CreationTimestamp
	private Timestamp certiRegDate;
	
}
