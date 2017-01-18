/*
* @Author: GaNeShKuMaRm
* @Date:   2017-01-13 10:56:39
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-01-13 19:29:34
*/

public class DES
{
    public static void main(String[] args)
    {
        TABLES tables = new TABLES();
        HELPERS helpers = new HELPERS();

        String message = "HELLO WORLD";
        String key = "PRINTER";

        String messageHEX = helpers.asciiToHex(message);
        String keyHEX = helpers.asciiToHex(key);

        String messageBIN = helpers.hexToBinary(messageHEX);
        System.out.println(messageBIN);
        String keyBIN = helpers.hexToBinary(keyHEX);

        System.out.println(keyBIN);
    }
}

