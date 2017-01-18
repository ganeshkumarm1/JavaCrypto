/*
* @Author: GaNeShKuMaRm
* @Date:   2017-01-13 18:08:27
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-01-13 19:25:29
*/

public class HELPERS
{
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
       // stringBIN = Long.toBinaryString(Long.parseLong(stringHEX, 16));
        for(int index = 0; index < stringHEX.length(); index ++)
        {
            stringBIN += Integer.toBinaryString(Integer.parseInt(Character.toString(stringHEX.charAt(index)), 16));
        }
        return stringBIN;
    }
}