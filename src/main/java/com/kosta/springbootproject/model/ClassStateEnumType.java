package com.kosta.springbootproject.model;

public enum ClassStateEnumType {
	APPLY("수강신청"),END("종강"),CLOSE("폐강");
	
	private String value;

	ClassStateEnumType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
