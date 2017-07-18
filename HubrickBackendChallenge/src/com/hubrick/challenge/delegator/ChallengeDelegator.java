package com.hubrick.challenge.delegator;

import java.io.IOException;

import com.hubrick.challenge.business.ChallengeBusiness;

/**
 * Delegates the data to business layer.
 *
 */
public class ChallengeDelegator {

	/**
	 * @param fileName
	 * @throws IOException
	 * 
	 * Delegates the data files to business layer method to process business logic
	 */
	public void delegateChallenge(String... fileName) throws IOException {
		final ChallengeBusiness business = new ChallengeBusiness();
		business.challengeBusiness(fileName);
	}

}
