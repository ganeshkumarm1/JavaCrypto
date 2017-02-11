/*
* @Author: GaNeShKuMaRm
* @Date:   2017-01-13 10:56:39
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-01-13 19:29:34
*/

import java.util.*;
import java.io.*;

public class DES
{
    public static String applyIPINV(String RnLn, TABLES tables)
    {
        final int[] IP_INV_TABLE = tables.IP_INV_TABLE;
        String messageBINFromIPINV = "";
        for(int index = 0; index < IP_INV_TABLE.length; index ++)
        {
            messageBINFromIPINV += RnLn.charAt(IP_INV_TABLE[index] - 1);
        }
        return messageBINFromIPINV;
    }
    
    public static String applyP(String messageBINFromS, TABLES tables)
    {
        final int[] P_TABLE = tables.P_TABLE;
        String messageBINFromP = "";
        for(int index = 0; index < P_TABLE.length; index ++)
        {
            messageBINFromP += messageBINFromS.charAt(P_TABLE[index] - 1);
        }
        //System.out.println(messageBINFromP.length());
        return messageBINFromP;
    }
    public static String applyS(String RnMinusOneFromETableXORKn, TABLES 
    tables, HELPERS helpers)
    {
        final int[][][] S_TABLE = tables.S_TABLE;
        String Sn = "";
        int start = 0, end = 6;
        //System.out.println(RnMinusOneFromETableXORKn);
        for(int i = 0; i < 8; i ++)
        {
            String split6Bit = RnMinusOneFromETableXORKn.substring(start, end);
            //System.out.println(split6Bit);
            start = end;
            end += 6;
            int row = Integer.parseInt(split6Bit.substring(0, 1) + split6Bit.substring(5, 6), 2);
            int col = Integer.parseInt(split6Bit.substring(1, 5), 2);
            String S_TABLEOutput = helpers.padZeros(Integer.toBinaryString(S_TABLE[i][row][col]));
            Sn += S_TABLEOutput;
            //System.out.println(row + " " + col);
            //System.out.println(S_TABLE[i][row][col]);
        }
        //System.out.println(Sn.length());
        return Sn;
    }
    
    public static String XOR(String RnMinusOneFromETable, String Kn)
    {   
        String RnMinusOneFromETableXORKn = "";
        for(int index = 0; index < RnMinusOneFromETable.length(); index ++)
        {
            if(RnMinusOneFromETable.charAt(index) == Kn.charAt(index))
            {
                RnMinusOneFromETableXORKn += '0';
            }
            else
            {
                RnMinusOneFromETableXORKn += '1';
            }
        }
        return RnMinusOneFromETableXORKn;
    }
    
    public static String applyE(String RnMinusOne, TABLES tables)
    {
        final int[] E_TABLE = tables.E_TABLE;    
        String RnMinusOneFromETable = "";
        for(int index = 0; index < E_TABLE.length; index ++)
        {
            RnMinusOneFromETable += RnMinusOne.charAt(E_TABLE[index] - 1);
        }
        return RnMinusOneFromETable;
    }
    
    public static String applyIP(String messageBIN, TABLES tables)
    {
        final int[] IP_TABLE = tables.IP_TABLE;
        String messageBINFromIP = "";
        for(int index = 0; index < IP_TABLE.length; index ++)
        {
            messageBINFromIP += messageBIN.charAt(IP_TABLE[index] - 1);
        }
        return messageBINFromIP;
    }
    
    public static List<String> applyPC2(List<String> shiftedKeys, TABLES tables)
    {
        final int[] PC2_TABLE = tables.PC2_TABLE;
        List<String> subKeys = new ArrayList<String>();
        for(int key = 0; key < shiftedKeys.size(); key ++)
        {
            String currentKey = shiftedKeys.get(key);
            String keyFromPC2 = "";
            for(int index = 0; index < PC2_TABLE.length; index ++)
            {
                //System.out.println(PC2_TABLE[index]);
                keyFromPC2 += currentKey.charAt(PC2_TABLE[index] - 1);
            }
            subKeys.add(keyFromPC2);
        }
        return subKeys;
    }
    
    public static List<String> applyPC1(String keyBIN, TABLES tables)
    {
        int[] numberOfShifts = new int[] {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};    
        final int[] PC1_TABLE = tables.PC1_TABLE;
        String keyBINFromPC1 = "";
        
        for(int index = 0; index < PC1_TABLE.length; index ++)
        {
            
            keyBINFromPC1 += keyBIN.charAt(PC1_TABLE[index] - 1);
        }
        
        String C0 = keyBINFromPC1.substring(0, 28);
        String D0 = keyBINFromPC1.substring(28, 56);
        List<String> shiftedKeys = new ArrayList<String>();

        for(int index = 0; index < numberOfShifts.length; index ++)
        {
            int shiftCount = numberOfShifts[index];
            C0 = C0.substring(shiftCount, 28) + C0.substring(0, shiftCount);
            D0 = D0.substring(shiftCount, 28) + D0.substring(0, shiftCount);
            shiftedKeys.add(C0 + D0);
        }
        return shiftedKeys;
    }
    
    public static List<String> splitMessagesIntoBlocks(String messageBIN)
    {
        List<String> messageblockList = new ArrayList<String>();
        int start = 0, end = 64;
        while(end <= messageBIN.length())
        {
            messageblockList.add(messageBIN.substring(start, end));
            start = end;
            end += 64;
        }
        return messageblockList;
    }
    
    public static void main(String[] args)
    {
        TABLES tables = new TABLES();
        HELPERS helpers = new HELPERS();

        String message = "HELLO WORLD";
        String key = "PRINTERS";

        String messageHEX = helpers.asciiToHex(message);
        String keyHEX = helpers.asciiToHex(key);

        //System.out.println(messageHEX);
        //System.out.println(keyHEX);
        String messageBIN = helpers.hexToBinary(messageHEX);
        String keyBIN = helpers.hexToBinary(keyHEX);
        
        //System.out.println(messageBIN);
        //System.out.println(keyBIN);
        
        while(keyBIN.length() % 64 != 0)
        {
            keyBIN += '0';
        }
        
        while(messageBIN.length() % 64 != 0)
        {
            messageBIN += '0';
        }
        
        //System.out.println(messageBIN);
        //System.out.println(keyBIN);
        
        //List<String> keyBIN_64BitList = splitIntoBlocks(keyBIN);
        List<String> messageBIN_64BitList = splitMessagesIntoBlocks(messageBIN);
        
        List<String> shiftedKeys = applyPC1(keyBIN, tables);
        List<String> subKeys = applyPC2(shiftedKeys, tables);

        String encryptedMessageBIN = "";
        String encryptedMessage = "";
        //System.out.println(messageBIN.length());        
        //System.out.println(subKeys);
        for(int i = 0; i < messageBIN_64BitList.size(); i ++)
        {   
            List<String> L = new ArrayList<String>();
            List<String> R = new ArrayList<String>();
            String messageBINBlock = messageBIN_64BitList.get(i);
            String messageBINFromIP = applyIP(messageBINBlock, tables);
            L.add(messageBINFromIP.substring(0, 32));
            R.add(messageBINFromIP.substring(32, 64));
            for(int index = 1; index <= 16; index ++)
            {
                String RnMinusOne = R.get(index - 1);
                L.add(RnMinusOne);
                String LnMinusOne = L.get(index - 1);
                String Kn = subKeys.get(index - 1);
                String RnMinusOneFromETable = applyE(RnMinusOne, tables);
                String RnMinusOneFromETableXORKn = XOR(RnMinusOneFromETable, Kn);
                String messageBINFromS = applyS(RnMinusOneFromETableXORKn, tables, helpers);
                String f = applyP(messageBINFromS, tables);
                String Rn = XOR(LnMinusOne, f);
                //System.out.println(Rn);         
                R.add(Rn);
                //System.out.println(RnMinusOneFromETable);
                //System.out.println(RnMinusOneFromETable.length());
            }
            String RnLn = R.get(16) + L.get(16);
            //System.out.println(RnLn);
            encryptedMessageBIN += applyIPINV(RnLn, tables); 
            String encryptedMessageHex = helpers.binaryToHex(encryptedMessageBIN);
            System.out.println(encryptedMessageHex);
            //encryptedMessage += helpers.hexToAscii(encryptedMessageHex);
            
            //System.out.println(RnLn.length());
            //System.out.println(L);
            //System.out.println(R);
        }
        //System.out.println(encryptedMessage);
        //System.out.println(messageBINFromIP);
        //System.out.println(messageBINFromIP.length());
        //System.out.println(subKeys);
        //System.out.println(messageBIN_64BitList);
    }
}

