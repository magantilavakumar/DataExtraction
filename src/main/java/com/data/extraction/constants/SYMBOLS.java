package com.data.extraction.constants;

public enum SYMBOLS {
	COMMA(","),BKWD_SLASH("\\"),DOT(".");
	
	SYMBOLS(String description) {
		this.description = description;
	}

	private String description;
	
	public String getDescription() {
		return description;
	}
}
