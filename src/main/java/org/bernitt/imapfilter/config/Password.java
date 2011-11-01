/**
 * 
 */
package org.bernitt.imapfilter.config;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author fbe
 * 
 */
public class Password {

	private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3',
			'4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static final String encryptionKey = "5f577821";

	public static String encryptPassword(String password) {

		try {
			SecretKey key = new SecretKeySpec(encryptionKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] utf8 = password.getBytes("UTF8");
			byte[] enc = cipher.doFinal(utf8);

			return toHexString(enc);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}

	}

	public static String decryptPassword(String encrypedPassword) {
		try {
			SecretKey key = new SecretKeySpec(encryptionKey.getBytes(), "DES");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);

			byte[] bytes = hexToBytes(encrypedPassword);
			byte[] dec = cipher.doFinal(bytes);

			return new String(dec, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (NoSuchPaddingException e) {
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RuntimeException(e);
		} catch (BadPaddingException e) {
			throw new RuntimeException(e);
		}

	}

	private static String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			sb.append(HEX_CHARS[(bytes[i] >> 4) & 0x0F]);
			sb.append(HEX_CHARS[bytes[i] & 0x0F]);
		}
		return sb.toString();
	}

	/**
	 * Converts a given hex string to a byte array.
	 * 
	 * @param hexString
	 *            the hex string
	 * @return converted hex string as byte array
	 * @throws IllegalAccessException
	 *             when the input string contains non-hexadecimal digits
	 */
	public static final byte[] hexToBytes(String hexString) {
		int length = hexString.length();
		if (length % 2 != 0) {
			throw new IllegalArgumentException(
					"(StringUtils#toBytes): input string is of uneven length "
							+ hexString);
		}

		byte[] data = new byte[length / 2];
		char highChar, lowChar;
		byte highNibble, lowNibble;
		for (int i = 0, offset = 0; i < length; i += 2, offset++) {
			highChar = hexString.charAt(i);
			if ((highChar >= '0') && (highChar <= '9')) {
				highNibble = (byte) (highChar - '0');
			} else if ((highChar >= 'A') && (highChar <= 'F')) {
				highNibble = (byte) (10 + highChar - 'A');
			} else if ((highChar >= 'a') && (highChar <= 'f')) {
				highNibble = (byte) (10 + highChar - 'a');
			} else {
				throw new IllegalArgumentException(
						"(StringUtils#toBytes): Invalid hex char: " + highChar);
			}

			lowChar = hexString.charAt(i + 1);
			if ((lowChar >= '0') && (lowChar <= '9')) {
				lowNibble = (byte) (lowChar - '0');
			} else if ((lowChar >= 'A') && (lowChar <= 'F')) {
				lowNibble = (byte) (10 + lowChar - 'A');
			} else if ((lowChar >= 'a') && (lowChar <= 'f')) {
				lowNibble = (byte) (10 + lowChar - 'a');
			} else {
				throw new IllegalArgumentException(
						"(StringUtils#toBytes): Invalid hex char: " + lowChar);
			}

			data[offset] = (byte) (highNibble << 4 | lowNibble);
		}
		return data;
	}

}
