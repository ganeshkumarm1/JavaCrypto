/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-11 21:18:19
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-12 00:33:12
*/

import java.io.*;
import java.net.*;

class Server
{
   public static void main(String argv[]) throws Exception
      {
         ServerSocket socket = new ServerSocket(6789);
         int client = 1;
         Long p, a, m, X = 0L, Y, K1;
         String request;
         DiffieHelmann DH = new DiffieHelmann();
         while(true)
         {
            Socket connectionSocket = socket.accept();
            BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            request = inFromClient.readLine();
            System.out.println("Request from client " + client);
            client ++;
            m = DH.generateRandom(10L, 50L);
            p = DH.generateRandomPrime(1000L, 10000L);
            a = DH.findPrimitiveRoot(p);
            X = DH.calculateMod(a, m, p);
            outToClient.writeBytes(Long.toString(p) + " " + Long.toString(a) + '\n');
            outToClient.flush();
            System.out.println("p: " + p + " " + "a: " + a);

            request = inFromClient.readLine();
            Y = Long.parseLong(request);
            System.out.println("Y: " + Y);
            outToClient.writeBytes(Long.toString(X) + '\n');
            outToClient.flush();

            K1 = DH.calculateMod(Y, m, p);
            System.out.println("K1: " + K1);
            //System.out.println(clientSentence);


            //System.out.println("Received: " + clientSentence);
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';

         }
      }
}