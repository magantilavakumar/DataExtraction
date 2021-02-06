/**
 * 
 */
package com.data.extraction.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.data.extraction.constants.KAFKA_TOPICS;

/**
 * @author magantilavakumar
 *
 */
@Component
public class CSVFileToJSONWriter implements ItemWriter<String> {
	
	private Logger logger = LoggerFactory.getLogger(CSVFileToJSONWriter.class);

	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	public CSVFileToJSONWriter() {
		super();
	}

	@Override
	public void write(List<? extends String> items) throws Exception {
		items.forEach(i -> kafkaTemplate.send(KAFKA_TOPICS.TOPIC_CSV_TO_JSON.getTopicDescription(), i));
//		if(items!=null && items.size()>1) {
//			List<String> lstRecords = null;
//			if(header==null) {
//				header = items.get(0).split(SYMBOLS.COMMA.getDescription(),-1);
//				lstRecords = items.stream().skip(1).collect(Collectors.toList());
//			}else {
//				lstRecords = new ArrayList<>(items);
//			}			
//			
//			StringBuilder sbPathBuilder = new StringBuilder();
//			if(Files.exists(Paths.get(sbPathBuilder.toString())));
//				sbPathBuilder.delete(0, sbPathBuilder.length());
//			sbPathBuilder.append(getCsvFilePath()).append(SYMBOLS.BKWD_SLASH.getDescription()).append(getCsvFileName().substring(0, getCsvFileName().indexOf(SYMBOLS.DOT.getDescription())));
//			if(!Files.exists(Paths.get(sbPathBuilder.toString())))
//				Files.createDirectory(Paths.get(sbPathBuilder.toString()));
//			Map<String,String> map = null;
//			for (String string : lstRecords) {
//				rownum = rownum + 1;				
//				sbPathBuilder.append(SYMBOLS.BKWD_SLASH.getDescription()).append(rownum).append(SYMBOLS.DOT.getDescription()).append(FILE_EXTENSIONS.JSON.name().toLowerCase());				
//				map =  new LinkedHashMap<String,String>();
//				String arryValues[] = string.split(SYMBOLS.COMMA.getDescription(),-1);	
//				
//				for(int i=0;i<header.length;i++)
//					map.put(header[i],arryValues[i]);
//				Path path = Paths.get(sbPathBuilder.toString());
//				Files.write(path, (objectMapper).writerWithDefaultPrettyPrinter().writeValueAsString(map).getBytes());
//
//			}
//		}
	}
	
	
}
