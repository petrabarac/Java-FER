package hr.fer.zemris.java.hw06.crypto;

/**
 * Class with two public static methods: hextobyte(keyText) and bytetohex(bytearray).
 * 
 * @author Petra Barać
 *
 */

public class Util {
	
	/**
	 * Method  take hex-encoded String and return appropriate byte[]
	 * @param text hex - encoded String
	 * @return byte array
	 */
	
	public static byte[] hextobyte(String text) {
	      String str = text;
	      byte[] val = new byte[str.length() / 2];
	      for (int i = 0; i < val.length; i++) {
	         int index = i * 2;
	         int j = Integer.parseInt(str.substring(index, index + 2), 16);
	         val[i] = (byte) j;
	      }
	   return val;
		
	}
	 /**
	  * Method takes a byte array and creates its hex-encoding
	  * @param array byte array
	  * @return hex - encoded String of given byte array
	  */
	public static String bytetohex(byte[] array) {
		StringBuilder strBuilder = new StringBuilder();
		for(byte val : array) {
			strBuilder.append(String.format("%02x", val&0xff));
		}
		return strBuilder.toString();
	}

}
