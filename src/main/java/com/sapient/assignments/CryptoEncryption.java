package com.sapient.assignments;
import java.util.Scanner;

public class CryptoEncryption {
	public static void main(String[] args) {
		CryptographyFunction crypto=new CryptographyFunction();
		System.out.println("Enter String");
		Scanner sc=new Scanner(System.in);
		String input=sc.next();
		System.out.println(crypto);
		String output=crypto.encrypt(input);
		System.out.println("Encrypted String is:"+output);
	}

}
