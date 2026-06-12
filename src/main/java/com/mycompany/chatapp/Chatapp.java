/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Student
 */
import java.util.Scanner; 

public class ChatApp {
    static String storedUsername = "";  

static String storedPassword = "";  

static String storedCellNumber = ""; 

public static boolean checkUserName(String username) { 
   if (username == null) return false; 
   boolean containsUnderscore = username.contains("_"); 
   boolean lengthIsValid = username.length() <= 5;  
   return containsUnderscore && lengthIsValid; 
} 
 
public static boolean checkPasswordComplexity(String password) { 
   if (password == null) return false; 
   return password.length() >= 8 &&  
          password.matches(".*[A-Z].*") &&  
          password.matches(".*\\d.*") &&  
          password.matches(".*[@#$%!?&].*"); 
} 
 
public static boolean checkCellPhoneNumber(String cellNumber) { 
   if (cellNumber == null) return false; 
   return cellNumber.matches("^\\+27\\d{9}$"); 
} 
 
public static String registerUser(String username, String password, String cellNumber) { 
   if (!checkUserName(username)) { 
       return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters long."; 
   } 
   if (!checkPasswordComplexity(password)) { 
       return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."; 
   } 
   if (!checkCellPhoneNumber(cellNumber)) { 
       return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again."; 
   } 
 
   storedUsername = username; 
   storedPassword = password; 
   storedCellNumber = cellNumber; 
 
   return "Welcome " + username + " it is great to see you."; 
} 
 
public static boolean loginUser(String username, String password) { 
   return username.equals(storedUsername) && password.equals(storedPassword); 
} 
 
public static void main(String[] args) { 
   Scanner input = new Scanner(System.in); 
 
   System.out.println("-----Register----"); 
   String username; 
   while (true) { 
       System.out.println("Enter username"); 
       username = input.nextLine(); 
       if (checkUserName(username)) { 
           System.out.println("Username successfully captured"); 
           break; 
       } 
   } 
 
   String password; 
   while (true) { 
       System.out.print("Enter password: "); 
       password = input.nextLine(); 
       if (checkPasswordComplexity(password)) { 
           break; 
       } 
   } 
 
   String cellNumber; 
   while (true) { 
       System.out.print("Enter cell number: "); 
       cellNumber = input.nextLine(); 
       if (checkCellPhoneNumber(cellNumber)) { 
           break; 
       } 
   } 
 
   System.out.println(registerUser(username, password, cellNumber)); 
 
   System.out.println("\n-----Login-----"); 
   System.out.print("Enter username: "); 
   String loginUser = input.nextLine(); 
 
   System.out.print("Enter password: "); 
   String loginPass = input.nextLine(); 
 
   if (!loginUser(loginUser, loginPass)) { 
       System.out.println("Login failed! Access denied."); 
       input.close(); 
       return; 
   } 
 
   // Functionality 2: Success welcome message 
   System.out.println("Login successful!"); 
   System.out.println("\nWelcome to QuickChat."); 
 
   // HAND OVER CONTROL TO THE EXTERNAL MESSAGE FILE 
   Message.startMessageSession(input); 
 
   input.close(); 
} 
 
    
}
