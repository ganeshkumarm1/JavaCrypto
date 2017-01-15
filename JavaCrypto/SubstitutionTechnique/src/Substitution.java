import java.util.*;
import java.io.*;

class Substitution 
{
    // Encryption
    public static String encrypt(String line)
    {
        char[] key = {'X', 'N', 'Y', 'A', 'H', 'P', 'O', 'G', 'Z', 'Q', 'W', 'B', 'T', 'S', 'F', 'L', 'R', 'C', 'V', 'M', 'U', 'E', 'K', 'J', 'D', 'I'};
        String temp = "";
        try
        {
            for(int i = 0; i < line.length(); i ++)
            {
                if(line.charAt(i) >= 'A' && line.charAt(i) <= 'Z')
                {
                    int index = (int) line.charAt(i);
                    temp += key[index - 65];
                }
                else
                {
                    temp += line.charAt(i);    
                }
                
            }    
            return temp;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        return temp;
    }
    
    //Read from file
    public static String readTextFile()
    {
            String line = null;
            String cipher_text = "";
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader("../plain_text.txt"));
                while((line = reader.readLine()) != null)
                {
                    cipher_text = cipher_text + encrypt(line.toUpperCase()) + '\n';
                }
                return cipher_text;
            }
            catch(Exception e) 
            {
                System.out.println(e);
            }
            return cipher_text;
    }
    
    //Write to File
    public static void writeTextFile(String cipher_text)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("../cipher_text.txt"));  
            System.out.println(cipher_text);
            writer.write(cipher_text);
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String args[]) 
    {
            String cipher_text = readTextFile();
            writeTextFile(cipher_text.trim());
    }
}