/**
 * This is a java program that tries to recover MD5 Hashes using 
 * a dictionary aka wordlist (not included)
 * @author Copyright 2007 rogeriopvl, <http://www.rogeriopvl.com>
 * @version 0.2
 * 
 * Changelog: 
 * v0.2 - minor code corrections, now with GUI hash prompt for better usability.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Instructions: Just compile it as a normal java class and run it. You will need
 * a dictionary file not included with the source. The file must have each word in a new line.
 * 
 * PERSONAL DISCLAIMER: This program was made exclusively for educational purposes, therefore
 * I can't be held responsible for the actions of others using it.
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;


public class MD5Recovery {

	private static final String PROGNAME = "MD5Recovery";
	private static final String VERSION = "v0.2";
	private static final int MD5_LENGTH = 32;
	private static final String EMPTY_HASH = "d41d8cd98f00b204e9800998ecf8427e";

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
	 * Generates an MD5 hash from a given string in the form of byte array
	 * @param string to generate the hash
	 * @return array of bytes representing the hash
	 */	
	private static byte[] generateMD5 (String str) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		byte[] hashMD5 = md.digest();

		return hashMD5;
	}

	/**
	 * Main method
	 * @param args command line arguments
	 * @throws FileNotFoundException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main (String [] args) throws FileNotFoundException, NoSuchAlgorithmException {

		if (args.length != 1) {
			System.err.println ("Usage: "+PROGNAME+" <dicionary file>");
			System.exit(1);
		}
		//let's ask for the hash to recover in a fancy dialog :)
		String hash = JOptionPane.showInputDialog(null, "MD5 hash to recover: ", PROGNAME+" "+VERSION, 1);
		
		if (hash.length() != MD5_LENGTH)
			System.err.println ("Error: not a valid MD5 hash, proceeding anyway...");
		
		Scanner infile = new Scanner(new FileReader (args[0]));

		String line;
		int count = 0;			

		while (infile.hasNext()) {
			line = infile.nextLine();
			count++;

			//no need to spend CPU cycles calculating this one...
			if (hash.equals(EMPTY_HASH)) {
				System.out.println ("Done it! That's the hash of an empty string!");
				System.exit(0);
			}
			if (hash.equals(byteToString(generateMD5(line)))) {
				System.out.println ("Houston, we've done it!: "+line);
				System.out.println ("Sucess after "+count+" words.");
				System.exit(0);
			}
		}
		System.out.println ("Unable to recover MD5 hash. Try using a better dictionary file.");
	}//end of main
}//end of class


