package com.trux.utils;
public class UnicORGIDGenerator {
 
   
    public static void main(String[] args) {
     	System.out.println(unicID());
    }
    public static String unicID(){
    	String alphaNumerics = "ABCDEFGHIJKLMNOPQRSWXYZ";
    	String t = "";
    	for (int i = 0; i < 4; i++) {
    	    t += alphaNumerics.charAt((int) (Math.random() * alphaNumerics.length()));
    	}
    	String numerics = "1234567890";
    	String t1 = "";
    	for (int i = 0; i < 2; i++) {
    	    t1 += numerics.charAt((int) (Math.random() * numerics.length()));
    	}
    	return t+t1;
    }
 
}