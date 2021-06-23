package com.kosta.springbootproject.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"mid", "mname"})
@Entity
@Table(name="tbl_members")
public class MemberDTO {
	
	@Id
	private String mid;
	private String mname;
	private String mpassword;
	
	@Enumerated(EnumType.STRING)
	private MemberRoleEnumType mrole;

}
