import java.util.*;

class CaesarCipher
{
    public static void decrypt(String cipher_text)
    {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        try 
        {
            int key = 0;
            while(key != 26)
            {
                String message = "";
                for(int i = 0; i < cipher_text.length(); i ++)
                {
                    int x = cipher_text.charAt(i);
                    message += letters[(x - key) % 26];
                }
                key ++;
                System.out.println("KEY: " + key + "\t" + "MESSAGE: " + message);
            }
        }
        catch(Exception e) 
        {
        }
    }
    public static void main(String args[])
    {
        String cipher_text = "YQIIKHUOEKYXQTHQJXUHRUJXUVYHIJCQDXUHUJXQDJXUIUSEDTCQDYDHECU";
        decrypt(cipher_text);
    }
}