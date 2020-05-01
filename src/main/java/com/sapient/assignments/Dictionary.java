package com.sapient.assignments;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Dictionary {

	public static void main(String[] args) {
		Map<String,Set<String>> dictionary = new TreeMap<>();
		dictionary.put("goodmorning", Set.of("Good Morning","Shubodaya","Shubhohday","Bonjour"));
	    dictionary.put("goodevening", Set.of("Good Evening","Shubha sange","Susandhya","Bonsoir"));
        Scanner scanner=new Scanner(System.in);
        int option;
        do {
    	   System.out.println("0.Exit");
    	   System.out.println("1.Enter String");
    	   System.out.println("Enter your option:1 or 0");
    	   option=scanner.nextInt();
    	   scanner.nextLine();
    	   if(option==1) {
    		   System.out.println("Enter string");
    		   String input=scanner.nextLine().trim().toLowerCase();
    		   if(dictionary.containsKey(input))
    		   {
    			   Set<String> synonyms=new HashSet<>(dictionary.get(input));
    			   System.out.println("Synonyms are:");
    			   for(String synonym:synonyms)
    			   {
    				   System.out.println(synonym);
    			   }
    		   }
    		   else
    		   {
    			   System.out.println("Input not in dictionary, Do u want to enter new word to dictionary?Y/N");
    			   if(scanner.nextLine().trim().equalsIgnoreCase("Y"))
    			   {   
   
    				   System.out.print("Enter Synonyms:");
    				   String synonym=scanner.nextLine().trim();
    				   Set<String> s=new HashSet<>();
    				   while(synonym.length()!=0)
    				   {
    					   s.add(synonym);
    					   synonym=scanner.nextLine().trim();
    				   }
    				   dictionary.put(input,s);
    			   }
    		   }
    	   }
    	    
       }while(option!=0);
      System.out.println("Dictionary keys in sorted order:"+dictionary.keySet());//Since considered map is TreeMap
       
	}

}
