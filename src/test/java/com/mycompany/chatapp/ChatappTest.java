/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class ChatAppTest {
    
    public ChatAppTest() { } 

@BeforeAll 
public static void setUpClass() { 
   // Runs once before all tests 
} 
@AfterAll 
public static void tearDownClass() { 
   // Runs once after all tests 
} 
 
@BeforeEach 
public void setUp() { 
   // Runs before every individual test 
} 
 
@AfterEach 
public void tearDown() { 
   // Runs after every individual test 
} 
 
/** 
* Test of checkUserName method. 
*/ 
@Test 
public void testCheckUserName() { 
   System.out.println("checkUserName"); 
   // Positive test: valid username 
   assertTrue(ChatApp.checkUserName("kyl_1")); 
    
   // Negative test: too long 
   assertFalse(ChatApp.checkUserName("kyle!!!!!!!")); 
} 
 
/** 
* Test of checkPasswordComplexity method. 
*/ 
@Test 
public void testCheckPasswordComplexity() { 
   System.out.println("checkPasswordComplexity"); 
   assertTrue(ChatApp.checkPasswordComplexity("P@ssw0rd1")); 
   assertFalse(ChatApp.checkPasswordComplexity("password")); 
} 
 
/** 
* Test of checkCellPhoneNumber method. 
*/ 
@Test 
public void testCheckCellPhoneNumber() { 
   System.out.println("checkCellPhoneNumber"); 
   assertTrue(ChatApp.checkCellPhoneNumber("+27123456789")); 
   assertFalse(ChatApp.checkCellPhoneNumber("0790374575")); 
} 
 
/** 
* Test of registerUser method. 
*/ 
@Test 
public void testRegisterUser() { 
   System.out.println("registerUser"); 
    
  String expectedWelcome = "Welcome kyl_1 it is great to see you."; 
   assertEquals(expectedWelcome, ChatApp.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976")); 
    
   // 2. Test Username Incorrect 
   String expectedUserErr = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters long."; 
   assertEquals(expectedUserErr, ChatApp.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976")); 
    
   // 3. Test Password Incorrect 
   String expectedPassErr = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character."; 
   assertEquals(expectedPassErr, ChatApp.registerUser("kyl_1", "password", "+27838968976")); 
    
   // 4. Test Cell Number Incorrect 
   String expectedCellErr = "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again."; 
   assertEquals(expectedCellErr, ChatApp.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553")); 
} 
 
}
