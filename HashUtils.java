/**
 * 
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {
	
	/**
	 * Converts an array of bytes to a String
	 * @param bytes the array of bytes to be converted
	 * @return the string converted
	 */	
	private static String byteToString (byte[] bytes) {

		StringBuilder s = new StringBuilder();

		for (int i=0; i<bytes.length;i++) {
			int highEnd = ((bytes[i]>>4) & 0xf)<<4;
			int lowEnd = bytes[i] & 0xf;

			if (highEnd == 0)
				s.append('0');
			s.append(Integer.toHexString(highEnd | lowEnd));
		}
		return s.toString();
	}

	/**
	 * Generates an Hash hash from a given string in the form of byte array
	 * @param string to generate the hash
	 * @param string the hashing algorithm to be used
	 * @return the string containing the hash
	 */	
	public static String generateHash (String str, String algo) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(algo);
		md.update(str.getBytes());

		return byteToString(md.digest());
	}
	
}