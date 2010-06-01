# MD5Recovery
# Info

This is a simple Java program that uses naive bruteforce to recover an MD5 hash. It uses a dictionary (not included). The results are as good as the dictionary used.

## Build and Run

You need the Java SDK installed. To compile just type in a terminal:

	javac MD5Recovery.java

To run MD5Recovery just type in a terminal:

	java MD5Recovery <path_to_the_dictionary_file>
	
If everything went ok, a window should popup asking for the hash that you wish to recover. Just insert the hash, proceed and wait :)

## Disclaimer

This program was developed for educational purposes only, and serves as a proof of concept for the fact that easy passwords with one word that is part of any normal dictionary are dangerous and should be avoided.