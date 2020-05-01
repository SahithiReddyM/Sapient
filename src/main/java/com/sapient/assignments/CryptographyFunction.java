package com.sapient.assignments;
import java.util.Arrays;

public class CryptographyFunction {
	private String[][] crypto_arr = new String[26][2];
	
	CryptographyFunction(){
		int[] primes=new int[26];
		short num_primes=0;byte p=1;
		while(num_primes!=26)
		{
			int flag=0;
			for(int i=2;i<=Math.sqrt(p) && flag==0;i++)
			{
				if(p%i==0)
				{
					flag=1;
				}
			}
			if(flag==0)
			{
				primes[num_primes++]=p;
			}
			p++;
		}
		int index=0;
		for(char d='A';d<='Z';d++)
		{
			crypto_arr[index][0]=Character.toString(d);
			crypto_arr[index][1]=Integer.toString(primes[index]);
			index++;
		}
		
	}
	public String encrypt(String input) {
		char[] inp_array=input.toCharArray();
		String encrypt_letter="";
		for(char letter:inp_array)
		{
			encrypt_letter+=Integer.toHexString(Integer.valueOf(crypto_arr[letter-'A'][1])).toUpperCase();
		}
		return encrypt_letter;
	}
	public String toString()
	{
		return Arrays.deepToString(crypto_arr);
	}
}
