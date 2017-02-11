/*
* @Author: GaNeShKuMaRm
* @Date:   2017-02-11 22:56:04
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-02-12 00:39:30
*/

import java.io.*;
import java.net.*;

class Client
{
    public static void main(String argv[]) throws Exception
    {
      String request;
      Long p, a, n, Y, X, K2;
      DiffieHelmann DH = new DiffieHelmann();
      BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
      Socket clientSocket = new Socket("localhost", 6789);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

      outToServer.writeBytes("p_and_a\n");
      outToServer.flush();
      request = inFromServer.readLine();
      p = Long.parseLong(request.split(" ")[0]);
      a = Long.parseLong(request.split(" ")[1]);
      n = DH.generateRandom(10L, 50L);
      Y = DH.calculateMod(a, n, p);

      System.out.println("p: " + p + " " + "a: " + a);
      outToServer.writeBytes(Long.toString(Y) + '\n');
      request = inFromServer.readLine();
      X = Long.parseLong(request);
      System.out.println("X: " + X);
      K2 = DH.calculateMod(X, n, p);
      System.out.println("K2: " + K2);
      clientSocket.close();
    }
}