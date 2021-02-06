package com.data.extraction.constants;

public enum CSV_CONSTANTS {
	HEADERS("@HEADERS");
	
	private String description;
	
	CSV_CONSTANTS(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
