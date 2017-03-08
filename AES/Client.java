import java.io.*;
import java.util.Scanner;
import java.net.*;

class Client
{
 public static void main(String argv[]) throws Exception
 {
  String request = "AES";
  String cipherText;
  Socket clientSocket = new Socket("localhost", 6788);
  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  outToServer.writeBytes(request + '\n');
  Scanner sc = new Scanner(System.in);
  cipherText = inFromServer.readLine();
  System.out.println(cipherText);
  System.out.print("Key: ");
  String key = sc.nextLine();
  AES aes = new AES();
  String decryptedText = aes.decrypt(cipherText, key);
  System.out.println(decryptedText);
  clientSocket.close();
 }
}