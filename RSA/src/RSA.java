/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-12 08:50:08
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-12 09:11:16
*/

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.PrivateKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import java.util.*;

public class RSA
{
    private KeyPair keyPair;
    public RSA() throws Exception
    {
        Initialize();
    }
    public void Initialize() throws Exception
    {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(512);
        keyPair = keygen.generateKeyPair();
    }
    public String encrypt(String plaintext)  throws Exception
    {
        PublicKey key = keyPair.getPublic();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes("UTF8"));
        return encodeBASE64(ciphertext);
    }
    public String decrypt(String ciphertext)  throws Exception
    {
        PrivateKey key = keyPair.getPrivate();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] plaintext = cipher.doFinal(decodeBASE64(ciphertext));
        return new String(plaintext, "UTF8");
    }
    private static String encodeBASE64(byte[] bytes)
    {
        BASE64Encoder b64 = new BASE64Encoder();
        return b64.encode(bytes);
    }

    private static byte[] decodeBASE64(String text) throws Exception
    {
        BASE64Decoder b64 = new BASE64Decoder();
        return b64.decodeBuffer(text);
    }
    public static void main(String[] args) throws Exception
    {
        RSA rsa = new RSA();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a line: ");
        java.io.InputStreamReader sreader = new java.io.InputStreamReader(System.in);
        java.io.BufferedReader breader = new java.io.BufferedReader(sreader);
        String plaintext = sc.nextLine();
        System.out.println("Plaintext = " + plaintext);
        String ciphertext = rsa.encrypt(plaintext);
        System.out.println("After Encryption Ciphertext = " + ciphertext);
        System.out.println("After Decryption Plaintext = " + rsa.decrypt(ciphertext));
    }

}