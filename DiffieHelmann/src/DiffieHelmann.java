/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-11 21:17:52
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-12 00:36:05
*/

import java.util.*;
import java.math.*;
import java.lang.*;

public class DiffieHelmann
{
    public static boolean isPrime(Long number)
    {
        for(Long i = 2L; i <= number / 2; i ++)
        {
            Long temp= number % i;
            if(temp == 0)
            {
                return false;
            }
        }
        return true;
    }

    public static Long generateRandom(Long min, Long max)
    {
        Random rand = new Random();
        Long randomNumber = min + ((long)(rand.nextDouble() * (max - min)));
        return randomNumber;
    }

    public static Long generateRandomPrime(Long min, Long max)
    {
        boolean prime = true;
        Long randomNumber = 0L;

        while(prime)
        {
            randomNumber = generateRandom(min, max);
            prime = !isPrime(randomNumber);
        }
        return randomNumber;
    }

    public static Long findPrimitiveRoot(Long p)
    {
        Long k, o = 1L;
        for(Long i = 2L; i < p; i ++)
        {
            k = (long) Math.pow((double) i, (double) o);
            k %= p;
            while(k > 1)
            {
                o ++;
                k *= i;
                k %= p;
            }
            if(o == p - 1)
            {
                return i;
            }
            o = 1L;
        }
        return -1L;
    }

    public static Long calculateMod(Long base, Long exp, Long p)
    {
        return ((long) Math.pow((double)base, (double)exp)) % p;
    }

    /*public static void main(String[] args)
    {
        try
        {
            Long p, a, m, n;
            Long X, Y;
            Long K1, K2;
            Scanner sc = new Scanner(System.in);
            p = generateRandomPrime(1000L, 100000L);
            a = findPrimitiveRoot(p);
            m = generateRandomPrime(10L, 50L);
            n = generateRandomPrime(10L, 50L);
            X = calculateMod(a, m, p);
            Y = calculateMod(a, n, p);
            K1 = calculateMod(Y, m, p);
            K2 = calculateMod(X, n, p);
            System.out.println("p: " + p + " " + "a: " + a);
            System.out.println("m: " + m + " " + "n: " + n);
            System.out.println("X: " + X + " " + "Y: " + Y);
            System.out.println("K1: " + K1 + " " + "K2: " + K2);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }*/
}