package util;

import java.security.MessageDigest;

/*
 * The util class performs simple mathematical calculations and encodings used in this program.
 * Thus, let's include the function that does SHA-256 calculation inside this Util class.
 * 
 */
public class Util {
	
	/*
	 * SHA hash algorithm takes the advantage of Avalanche Effect, so it is very hard to find out the original input with the output.
	 * Block chain's mining principle starts from this Avalanche Effect.
	 * Takes the input from the user to apply SHA-256 hash algorithm to return the result as an array of bytes.
	 */
	public static String getHash(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(input.getBytes());
			byte[] bytes= md.digest();
			for (int i = 0; i < bytes.length; i++)
				result.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result.toString();
	}
}
