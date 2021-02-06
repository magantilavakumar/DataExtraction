/**
 * 
 */
package com.data.extraction.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;

import com.data.extraction.mapper.ConvertCSVToJSONLineMapper;
import com.data.extraction.policy.CustomSkipPolicy;
import com.data.extraction.processor.CSVFileProcessor;
import com.data.extraction.writer.CSVFileToJSONWriter;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author magantilavakumar
 *
 */
@Configuration
@EnableBatchProcessing
public class ProcessCSVFileConfig {
	
	private Logger log = LoggerFactory.getLogger(ProcessCSVFileConfig.class);
	
	@NonNull
	@Value(value = "${CSV.File}")
	Resource resource;
	
	@Autowired
	@Bean(name = "csvFileExtractionJobProces")
	
	public Job initCSVJobProcess(JobBuilderFactory jobBuildForCSVProcess,Step csvStepExtract){
		log.info("initCSVJobProcess");
	        return jobBuildForCSVProcess.get("CSV Job Process")
	        			.incrementer(new RunIdIncrementer())
	        			.start(csvStepExtract)	        			
	        			.build();
	}

	@Autowired
	@Bean
	public Step initStepToProcessCSFile(StepBuilderFactory stepBuildForCSVProcess,CSVFileProcessor csvFileProcessor,CSVFileToJSONWriter csvFileToJSONWriter) throws IOException {
		log.info("initStepToProcessCSFile");
	        return stepBuildForCSVProcess.get("Extract CSV File")
                    .<String,String>chunk(3)
                    .reader(csvFlatFileItemReader())
                    .faultTolerant().skipPolicy(new CustomSkipPolicy())
                    .processor(csvFileProcessor)
                    .writer(csvFileToJSONWriter)
                    .build();
	}	
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	
	
	@Bean
	public FlatFileItemReader<String> csvFlatFileItemReader() {		
		FlatFileItemReader<String> reader = new FlatFileItemReader<>();
		reader.setResource(resource);		
		reader.setName(resource.getFilename());
        reader.setLineMapper(new ConvertCSVToJSONLineMapper());
        return reader;
	}
}
