package com.chens.workflow.enums;

public enum ConditionEnum {
	EQ("等于", "EQ"), 
	GE("大于等于","GE"),
	GT("大于","GT");
	
	
	private String display;
	private String code;
	
	private ConditionEnum(String display, String code ) {
		this.display = display;
		this.code = code;
		
	}
	public String getDisplay() {
		return display;
	}

	public String getCode() {
		return code;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
