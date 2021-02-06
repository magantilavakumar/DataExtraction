/**
 * 
 */
package com.data.extraction.policy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;

/**
 * @author magantilavakumar
 *
 */
@Component
public class CustomSkipPolicy implements SkipPolicy {

	/**
	 * 
	 */
	public CustomSkipPolicy() {
		super();
	}

	@Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		boolean output = true;
		if(skipCount>2) {
			output = false;
		}
		return output;
	}

}
