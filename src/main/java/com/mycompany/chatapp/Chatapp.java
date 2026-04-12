/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Chatapp {

 
static String storedUsername = ""; 
static String storedPassword = ""; 
static String storedCellNumber = ""; 
//username method 
static boolean checkUserName(String username){ 
    if (username.length()>5){ 
        System.out.println("Username must not exceed 5 characters."); 
        return false; 
    } 
    if (!username.contains("_")){ 
        System.out.println("Username must contain an underscore(_)."); 
    return false; 
    } 
    return true; 
} 
//passwordComplexity method  
static boolean checkPasswordComplexity(String password){ 
    if(password.length()<8){ 
        System.out.println("Password must be at least 8 characters long."); 
        return false; 
    } 
    if(!password.matches(".*[A-Z].*")){ 
        System.out.println("Password must contain a capital letter."); 
        return false; 
    } 
    if(!password.matches(".*\\d.*")){ 
        System.out.println("Passsword must have at least one number."); 
        return false; 
    } 
    if(!password.matches(".*[@#$%!?&].*")){ 
        System.out.println("Password must have a special character."); 
        return false; 
    } 
    return true; 
} 
//checkCellphoneNUmber method 
static boolean checkCellPhoneNumber(String cellNumber){ 
    if(cellNumber.matches("^\\+27[0-9]{9}$")){ 
        return true; 
    } 
    System.out.println("Cell number must be in the format: +27XXXXXXXXX"); 
    return false; 
} 
//registering the user 
static String registerUser(String username, String password, String cellNumber){ 
    if (!checkUserName(username)){ 
        return "Registration failed: username is incorrect."; 
    } 
    if(!checkPasswordComplexity(password)){ 
        return "Registration failed: incorrectly formatted password."; 
    } 
    if(!checkCellPhoneNumber(cellNumber)){ 
        return "Registration failed: cell number is incorrectly formatted."; 
    } 
     
    storedPassword = password; 
    storedUsername = username; 
    storedCellNumber = cellNumber; 
     
    return "Registration successful! Welcome to ChatApp, " + username + "."; 
} 
 
 
public static void main(String[] args) { 
 
    Scanner input = new Scanner(System.in); 
    
    String username; 
    String cellNumber; 
    String password; 
    
    
    // Registration 
    System.out.println("-----Register----"); 
 
    System.out.print("Enter username: "); 
     username = input.nextLine(); 
 
   
     while(true){ 
         System.out.println("Enter password: "); 
         password = input.nextLine(); 
          
         String pattern ="^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$"; 
          
         if (password.length()<8){ 
             System.out.println("Password must be at least 8 characters long"); 
         } 
         else if(!password.matches(".*[A-Z].*")){ 
             System.out.println("Password must contain a capital letter"); 
         } 
         else if(!password.matches(".*\\d.*")){ 
             System.out.println("Password must have at least one number"); 
         } 
         else if(!password.matches(".*[@$!%*?&].*")){ 
             System.out.println("Password must have a special character"); 
         } 
         else{ 
             System.out.println("Password is correctly formatted"); 
             break; 
         } 
     } 
      
    while(true){ 
        System.out.println("Enter cell number: "); 
        cellNumber = input.nextLine(); 
        
        if (cellNumber.matches("^(?:\\+27|0)[6-8][0-9]{8}$")) { 
            System.out.println("Cell number correctly formatted!"); 
            break; 
        } else{ 
            System.out.println("Cell number incorrectly formatted!"); 
        } 
    } 
 
    System.out.println("Registration successful!\n"); 
 
    // Login 
    System.out.println("Login"); 
 
    System.out.println("Enter username: "); 
    String loginUser = input.nextLine(); 
 
    System.out.println("Enter password: "); 
    String loginPass = input.nextLine(); 
 
    if (loginUser.equals(username) && loginPass.equals(password)) { 
        System.out.println("Login successful!"); 
        System.out.println("Registration Complete! Welcome to ChatApp " + username);
    }
    else { 
        System.out.println("Login failed!"); 
    } 
  }
}
