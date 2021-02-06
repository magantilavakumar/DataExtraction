/**
 * 
 */
package com.data.extraction.mapper;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

import com.data.extraction.constants.CSV_CONSTANTS;


/**
 * @author magantilavakumar
 *
 */
@Component
public class ConvertCSVToJSONLineMapper implements LineMapper<String> {

	
	private String[] headers;

	public ConvertCSVToJSONLineMapper() {
		super();
	}

	/*
	 * Purpose: Convert the String line to Map
	 */
	@Override
	public String mapLine(String line, int lineNumber) throws Exception {
		if(lineNumber == 1) {
			line = CSV_CONSTANTS.HEADERS.getDescription()+line;
		}
		return line;
	}
	
	

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

}
