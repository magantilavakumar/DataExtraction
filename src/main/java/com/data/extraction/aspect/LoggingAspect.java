/**
 * 
 */
package com.data.extraction.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author magantilavakumar
 *
 */
@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("@annotation(Logit)")
	public void exeuteLogit() {
		
	}
	
	@Before("exeuteLogit()")
	public void beforeExecute(JoinPoint pJP) {
		StringBuilder stBuild = new StringBuilder();
		
		if(LOGGER.isInfoEnabled()){
			Object[] objArg = pJP.getArgs();
			for (Object object : objArg) {
				stBuild.append("before:"+object);
			}
			
			LOGGER.info(stBuild.toString());
		}
	}
	

	@Before("execution(* com.data.extraction.writer.CSVFileToJSONWriter.*(..)) and args(items)")
	public void executeLogit(JoinPoint joinPoint,List<String> items) {
		StringBuilder stBuild = new StringBuilder();
		
		if(LOGGER.isInfoEnabled()){
			stBuild.append("Item:"+items);
			LOGGER.info(stBuild.toString());
		}
	}
	
	
}
