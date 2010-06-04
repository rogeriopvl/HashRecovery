/**
 * This is a java program that tries to recover crypto hashes using 
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
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;


public class HashRecovery {

	private static final String PROGNAME = "HashRecovery";
	private static final String VERSION = "v0.5";
	private static final int MIN_HASH_LENGTH = 32;
	private static final String EMPTY_HASH = "d41d8cd98f00b204e9800998ecf8427e";

	/**
	 * Main method
	 * @param args command line arguments
	 * @throws FileNotFoundException
	 */
	public static void main (String [] args) throws FileNotFoundException {

		String algo = null;
		String dictionaryPath = null;

		if (args.length < 1) {
			usage();
		}
		else if (args[0].equals("-a")) {
			if (args.length != 3) {
				usage();
			}
			else {
				algo = args[1];
				dictionaryPath = args[2];
			}
		}
		else {
			usage();
		}
		
		//let's ask for the hash to recover in a fancy dialog :)
		String hash = JOptionPane.showInputDialog(null, "Hash to recover: ", PROGNAME+" "+VERSION, 1);
		
		if (hash.length() < MIN_HASH_LENGTH)
			System.err.println ("Warning: probably not a valid hash, proceeding anyway...");
		
		Scanner infile = new Scanner(new FileReader(dictionaryPath));

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
			
			try {
				if (hash.equals(HashUtils.generateHash(line, algo))) {
					System.out.println ("Houston, we've done it!: "+line);
					System.out.println ("Sucess after "+count+" words.");
					System.exit(0);
				}
			}
			catch(NoSuchAlgorithmException e) {
				System.err.println("Error: Unsupported algorithm - "+algo);
				System.exit(1);
			}
		}
		infile.close();
		System.out.println ("Unable to recover hash. Try using a better dictionary file.");
	}//end of main
	
	/**
	 * Prints the usage example and exits the program
	 */
	private static void usage() {
		System.err.println ("Usage: "+PROGNAME+" [-a <algo>] <dictionary file>");
		System.exit(1);
	}
}//end of class


