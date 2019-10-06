package hr.fer.zemris.java.hw06.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Program Crypto  allow the user to encrypt/decrypt given file using the AES crypto-
 * algorithm and the 128-bit encryption key or calculate and check the SHA-256 file digest.
 * 
 * @author Petra Barać
 *
 */
public class Crypto {

	/**
	 * Metod parse command-line arguments and check what function does user want. Allowed ones are "checksha" "encrypt" and "decrypt".
	 * 
	 * @param args ommand-line arguments
	 * @throws IllegalArgumentException if it is used unsupported ommand-line arguments
	 */
	public static void main(String[] args) {

		
		switch (args[0]) {
		case "checksha":
				checkSha(args[1]);			
				break;
		case "encrypt":
				crypting(args[0], args[1], args[2]);
				break;
		case "decrypt":
				crypting(args[0], args[1], args[2]);
				break;
		default:
			throw new IllegalArgumentException("You can ask for command checksha, encrypt and decrypt");
		}	
	}
	
	/**
	 * Metod check given mood (ncrypting or decrypting), than takes from user a hex - encoded text and initialization vector
	 * that are byte-arrays each having 16 bytes.
	 * Depending on mood method do encrypting or decrypting of  given file and resoult write in cryptFile.
	 * Encryption is the conversion of data into a form, called a ciphertext, that can not be easily understood by 
	 * unauthorized people. Decryption is the reverse process: it is a transformation of the ciphertext back into its 
	 * original form.
	 * 
	 * 
	 * @param mood holds name of method
	 * @param file name of faile to be encrypt or decrypt
	 * @param cryptFile file where encrypted / decrypted data will be written
	 */
	
	public static void crypting(String mood, String file, String cryptFile) {
		
		boolean encrypt = true;
		 if(mood.toLowerCase().equals("decrypt")) {
			 encrypt = false;
		 }
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits): ");
		System.out.print("> ");
		
		String userPassword = sc.next();
		
		System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
		System.out.print("> ");
		String iv = sc.next();

	    String keyText = userPassword;
	    String ivText = iv;
	    SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
	    AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hextobyte(ivText));
	    Cipher cipher = null;
	    

		// Initialize the cipher
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);			
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		
		
		Path p = Paths.get(file);
		Path p2 = Paths.get(cryptFile);
		
		try (InputStream is = Files.newInputStream(p)) {
			OutputStream output = Files.newOutputStream(p2);
			byte[] buff = new byte[1024];
			while(true) {
				int r = is.read(buff);
				if(r<1) break;
				output.write(cipher.update(buff, 0, r));
			}
			output.write(cipher.doFinal());
		} catch(IOException | IllegalBlockSizeException | BadPaddingException e) {
		System.out.println(e.getMessage());
		}		

		sc.close();
		if(encrypt) {
			System.out.println("Decryption completed. Generated file " + cryptFile + " based on file " + file);

		} else {
			System.out.println("Decryption completed. Generated file " +  cryptFile + " based on file " + file);

		}
	}
	
	/**
	 * Method for calculating file messageDigest.
	 *  Digests are used to verify if the data you have received (for example, when downloading the data from the Internet) arrived unchanged.
	 *  
	 * This is verify by calculating the digest on the file you have downloaded and then comparing the calculated digest with the digest
	 *  which is published on the web site from which you have started the download. If something has changed during the download, there is
	 *  extremely high probability that the calculated digest will be different from the one published on the web site
	 * 
	 * 
	 * @param file file to check messageDigest
	 */
			
	public static void checkSha(String file) {
		Path p = Paths.get(file);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please provide expected sha-256 digest for hw06test.bin:");
		System.out.print("> ");
			
		byte[] userDigest = Util.hextobyte(sc.next());
			
		
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
		byte[] digest = null;
		try (InputStream is = Files.newInputStream(p)) {
			byte[] buff = new byte[1024];
			while(true) {
				int r = is.read(buff);
				if(r<1) break;
				sha.update(buff, 0, r);
			}
		} catch(IOException ex) {
		System.out.println(ex.getMessage());
		}
			
		digest = sha.digest();
		
		if(MessageDigest.isEqual(digest, userDigest)) {
			System.out.println("Digesting completed. Digest of hw06test.bin matches expected digest.");
		} else {
			System.out.println("Digesting completed. Digest of " + file + " does not match the expected digest. Digest was: " + Util.bytetohex(digest));
		}		 
		sc.close();
			
	}
	
}
