/*
* @Author: GaNeShKuMaRm
* @Date:   2017-01-13 18:08:27
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-01-13 19:25:29
*/
import java.math.BigInteger;

public class HELPERS
{
    public String padZeros(String stringBIN)
    {
        if(stringBIN.length() == 4)
        {
            return stringBIN;
        }
        if(stringBIN.length() == 3)
        {
            return "0" + stringBIN;
        }
        if(stringBIN.length() == 2)
        {
            return "00" + stringBIN;
        }
        if(stringBIN.length() == 1)
        {
            return "000" + stringBIN;
        }
        return stringBIN;   
    }
    
    
    public String asciiToHex(String string)
    {
        String stringHEX = "";
        for(int index = 0; index < string.length(); index ++)
        {
            stringHEX += Integer.toHexString((int) string.charAt(index));
        }
        return stringHEX;
    }

    public String hexToBinary(String stringHEX)
    {
        String stringBIN = "";
        for(int index = 0; index < stringHEX.length(); index ++)
        {
            String temp = Integer.toBinaryString(Integer.parseInt(Character.toString(stringHEX.charAt(index)), 16));
            stringBIN += padZeros(temp);
        }
        return stringBIN;
    }
    
    public String binaryToHex(String stringBIN)
    {
        BigInteger decimal = new BigInteger(stringBIN, 2);
        BigInteger hex = new BigInteger(decimal, 10);
        //System.out.println(hexStr);
        return new String(hex.toByteArray());//hexStr;
    }
    
    public String hexToAscii(String stringHEX)
    {
        String str = "";
        for(int i = 0; i < stringHEX.length() ; i += 2)
        {
            String s = stringHEX.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            str = str + (char) decimal;
        }          
        return str;
    }
}