import java.security.*;
import java.util.*;
import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import sun.misc.*;

class AES
{
	public SecretKeySpec getKey(String key) throws Exception
	{
		byte[] bytes = key.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		bytes = sha.digest(bytes);
		bytes = Arrays.copyOf(bytes, 16);
		SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
		return secretKeySpec;
	}

	public String encrypt(String plainText, String key) throws Exception
	{
		SecretKeySpec AESKey = getKey(key);
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, AESKey);
		byte[] encVal = c.doFinal(plainText.getBytes());
		String cipherText = new BASE64Encoder().encode(encVal);
		return cipherText;
	}
	public String decrypt(String cipherText, String key) throws Exception
	{
		SecretKeySpec AESKey = getKey(key);
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, AESKey);
		byte[] decodedVal = new BASE64Decoder().decodeBuffer(cipherText);
		byte[] decVal = c.doFinal(decodedVal);
		String decryptedText = new String(decVal);
		return decryptedText;
	}
}