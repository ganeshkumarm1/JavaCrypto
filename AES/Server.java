import java.io.*;
import java.net.*;
import java.util.Scanner;
class Server
{
   public static void main(String argv[]) throws Exception
      {
         String key, request;
         String plainText = "", line = "";
         ServerSocket welcomeSocket = new ServerSocket(6789);
         while(true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            Scanner sc = new Scanner(System.in);
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            request = inFromClient.readLine();
            plainText = new Scanner(new File("../plain_text.txt")).useDelimiter("\\Z").next();
            System.out.println(plainText);
            System.out.print("Key: ");
            key = sc.nextLine();
            AES aes = new AES();
            String cipherText = aes.encrypt(plainText, key);
            System.out.println(cipherText);
            outToClient.writeBytes(cipherText + '\n');
         }
      }
}