/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-15 21:28:15
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-15 22:39:48
*/

import java.math.*;
import java.util.*;

public class RSA
{
    public static BigInteger generateE(BigInteger phi)
    {
        BigInteger begin = new BigInteger("3");
        while(begin.compareTo(phi) < 0)
        {
            if(isCoPrime(begin, phi))
                break;
            begin = begin.add(BigInteger.ONE);
            System.out.println(begin);
        }
        return begin;
    }

    public static BigInteger gcd(BigInteger n1, BigInteger n2)
    {
       if(n1 == BigInteger.ZERO)
			return n2;
		return gcd(n2.mod(n1),n1);
    }

    public static boolean isCoPrime(BigInteger n1, BigInteger n2)
    {
        if(gcd(n1,n2).equals(BigInteger.ONE))
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        BigInteger p, q, n, phi, e, d, m, cipherText, decryptedMessage;
	String plainText;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter p: ");
        p = new BigInteger("32416189381");
        //p = new BigInteger(sc.nextLine());
        System.out.println("Enter q: ");
        //q = new BigInteger(sc.nextLine());
        q = new BigInteger("32416190071");
        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
        e = generateE(phi);
        d = e.modInverse(phi);
	    System.out.println("Plain Text: ");
	    plainText = sc.nextLine();
	    m = new BigInteger(plainText.getBytes());
	    cipherText = m.modPow(e, n);
	    System.out.println("Cipher Text: " + new String(cipherText.toByteArray()));
	    decryptedMessage = cipherText.modPow(d, n);
	    System.out.println("Decrypted Message: " + new String(decryptedMessage.toByteArray()));
    }
}