/**
 * 
 */
package com.data.extraction.constants;

/**
 * @author magantilavakumar
 *
 */
public enum KAFKA_TOPICS {
	TOPIC_CSV_TO_JSON("tp-convert-csv-json");
	
	private String topicDescription;
	
	private KAFKA_TOPICS(String topicDescription) {
		this.topicDescription = topicDescription;
	}
	
	public String getTopicDescription() {
		return topicDescription;
	}
}
