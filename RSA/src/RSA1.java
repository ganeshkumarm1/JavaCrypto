/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-15 21:28:15
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-15 22:39:48
*/

import java.math.*;
import java.util.*;

public class RSA1
{
    public static BigInteger generateE(BigInteger phi)
    {
        BigInteger begin = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        while(begin.compareTo(phi) == 0)
        {
            if(isCoPrime(begin, phi))
                break;
            begin.add(one);
            System.out.println(begin);
        }
        return begin;
    }

    public static BigInteger gcd(BigInteger n1, BigInteger n2)
    {
        while(n1.compareTo(n2) != 0)
        {
            if(n1.compareTo(n2) == 1)
                n1 = n1.subtract(n2);
            else
                n2 = n2.subtract(n1);
        }
        return n1;
    }

    public static boolean isCoPrime(BigInteger n1, BigInteger n2)
    {
        if(gcd(n1,n2).compareTo(BigInteger.ONE) == 0)
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        BigInteger p, q, n, phi, e, d;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter p: ");
        p = new BigInteger(sc.nextLine());
        System.out.println("Enter q: ");
        q = new BigInteger(sc.nextLine());
        n = p.multiply(q);
        phi = (p.subtract(BigInteger.ONE)).multiply((q.subtract(BigInteger.ONE)));
        BigInteger x = new BigInteger("2");
        e = generateE(phi);
        d = e.modInverse(phi);
        System.out.println(d);
    }
}