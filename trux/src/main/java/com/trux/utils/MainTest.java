package com.trux.utils;

import java.util.Random;

public class MainTest {
	public static synchronized String generateUniqueToken(Integer length){ 
	    byte random[] = new byte[length];
	    Random randomGenerator = new Random();
	    StringBuffer buffer = new StringBuffer();

	    randomGenerator.nextBytes(random);

	    for (int j = 0; j < random.length; j++) {
	        byte b1 = (byte) ((random[j] & 0xf0) >> 4);
	        byte b2 = (byte) (random[j] & 0x0f);
	        if (b1 < 10)
	            buffer.append((char) ('0' + b1));
	        else
	            buffer.append((char) ('A' + (b1 - 10)));
	        if (b2 < 10)
	            buffer.append((char) ('0' + b2));
	        else
	            buffer.append((char) ('A' + (b2 - 10)));
	    }
	    return (buffer.toString());
	}

public static void main(String[] args) {
	System.out.println("bb-16-bb-3277".toUpperCase());
}
	/*public static String plate()
	{
	    int x = 6;
	    char[] plate = new char[x];
	    int c  = 'A';            
	    for(int p = 0; p < 6; p++)
	    {
	        int vehiclePlate = 0 + (int) (Math.random()* 6);
	        switch(vehiclePlate)
	        {
	            case 0: c = '0' +  (int)(Math.random() * 10); break;
	            case 1: c = 'A' +  (int)(Math.random() * 26); break;
	        }
	        plate[p] = (char)c;
	    }
	    return new String(plate);
	}
	private static Random random = new Random((new Date()).getTime());

    public static String generateRandomString(int length) {
      char[] values = {'A','B','C','D','E','F','G','H','I','J',
               'K','L','M','N','O','P','Q','R','S','T',
               'U','V','W','X','Y','Z'};

      String out = "";
      for (int i=0;i<length;i++) {
          int idx=random.nextInt(values.length);
          out += values[idx];
      }
      return out;
    }
    
    
    private static Random random1 = new Random((new Date()).getTime());

    public static String generateRandomNumber(int length) {
      char[] values = {'0','1','2','3',
    		    '4','5','6','7','8','9'};

      String out = "";
      for (int i=0;i<length;i++) {
          int idx=random1.nextInt(values.length);
          out += values[idx];
      }
      return out;
    }
    '0','1','2','3',
    '4','5','6','7','8','9'
public static void main(String[] args) {
String uu=generateRandomString(4);
String nn=""+generateRandomNumber(2);

//plate();
System.out.println(plate());
}*/
}
