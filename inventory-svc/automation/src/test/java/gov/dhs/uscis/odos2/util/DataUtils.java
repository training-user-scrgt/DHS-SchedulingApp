package gov.dhs.uscis.odos2.util;

import java.util.Random;

public class DataUtils {
	
	/**
	 * Get a random string
	 * 
	 * @param length string length
	 * @return
	 */
	public static String getRandomString(int length) {
		String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(charPool.charAt(random.nextInt(charPool.length())));
		}
		return sb.toString();
	}
}
