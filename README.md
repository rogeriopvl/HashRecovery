# HashRecovery
# Info

This is a simple Java program that uses naive bruteforce to recover an Hash hash. It uses a dictionary (not included). The results are as good as the dictionary used.

## Build and Run

You need the Java SDK installed. To compile just type in a terminal:

	javac HashUtils.java HashRecovery.java

To run HashRecovery just type in a terminal:

	java HashRecovery [-a <algo>] - p <path_to_the_dictionary_file>
	
The parameter `<algo>` is the hashing algorithm of your choice. You can choose between `MD2`, `Hash`, `SHA-1`, `SHA-256`, `SHA-384` and `SHA-512`. If you don't provide this parameter `Hash` will be used as default.
	
If everything went ok, a window should popup asking for the hash that you wish to recover. Just insert the hash, proceed and wait :)

## Why the !#%$ you used Java for this?

This code was made some time ago, during University. And since the language I most used and felt confortable with was Java, that's what I chose :)

## Disclaimer

This program was developed for educational purposes only, and serves as a proof of concept for the fact that easy passwords with one word that is part of any normal dictionary are dangerous and should be avoided.