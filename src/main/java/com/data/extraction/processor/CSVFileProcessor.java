package com.data.extraction.processor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.extraction.aspect.Logit;
import com.data.extraction.constants.CSV_CONSTANTS;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CSVFileProcessor implements ItemProcessor<String, String> {

	private String[] headers;

	@Autowired
	ObjectMapper objectMapper;
	
	public CSVFileProcessor() {
		super();
	}
	
	@Logit
	@Override
	public String process(String item) throws Exception {
		if(item!=null) {
			Map<String,String> valueMap = null;
			if(item.indexOf(CSV_CONSTANTS.HEADERS.getDescription())>=0) {
				item = item.substring(CSV_CONSTANTS.HEADERS.getDescription().length());
				headers = item.split(",",-1);
				item = null;
			}else {
				valueMap = new LinkedHashMap<>();
				String[] values = item.split(",", -1);
				for (int i=0;i<headers.length;i++) {
					valueMap.put(headers[i], values[i]);
				}
				item = objectMapper.writeValueAsString(valueMap);
			}
		}		
		return item;
	}



	public String[] getHeaders() {
		return headers;
	}



	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

}
