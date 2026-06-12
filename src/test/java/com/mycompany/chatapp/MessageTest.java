/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;
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
public class MessageTest { 
   public MessageTest() {
    } 

    @BeforeAll 
    public static void setUpClass() { 
    } 

    @AfterAll 

    public static void tearDownClass() { 

    } 
    @BeforeEach 

    public void setUp() { 

        Message.clearArraysForTesting(); 

         

        // Populate system arrays with test data entries from Message 1 to Message 5 

        Message.populateTestDataDirectly("1000000001", "+27834557896", "Did you get the cake?", "Sent"); 
        Message.populateTestDataDirectly("1000000002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"); 
        Message.populateTestDataDirectly("1000000003", "+27834484567", "Yohoooo, I am at your gate.", "Disregard"); 
        Message.populateTestDataDirectly("0838884567",  "+27838884567", "It is dinner time !", "Sent"); 
        Message.populateTestDataDirectly("1000000005", "+27838884567", "Ok, I am leaving without you.", "Stored"); 

    } 

     

    @AfterEach 

    public void tearDown() { 

    } 

  

    @Test 

    public void testSentMessagesArrayCorrectlyPopulated() { 

        System.out.println("Test: Sent Messages array correctly populated"); 

        String expected = "Did you get the cake?, It is dinner time !"; 

        assertEquals(expected, Message.getSentMessagesListReport()); 

    } 

  

    @Test 

    public void testDisplayTheLongestMessage() { 

        System.out.println("Test: Display the longest Message"); 

        String expected = "Where are you? You are late! I have asked you to be on time."; 

        assertEquals(expected, Message.getLongestStoredMessageText()); 

    } 

  

    @Test 

    public void testSearchForMessageID() { 

        System.out.println("Test: Search for messageID"); 

        String expected = "It is dinner time !"; 

        assertEquals(expected, Message.searchByMessageID("0838884567")); 

    } 

  

    @Test 

    public void testSearchAllMessagesSentOrStoredRegardingAParticularRecipient() { 

        System.out.println("Test: Search all messages regarding a particular recipient"); 

        String result = Message.searchAllByRecipient("+27838884567"); 

        assertTrue(result.contains("Where are you? You are late!")); 

        assertTrue(result.contains("It is dinner time !")); 

        assertTrue(result.contains("Ok, I am leaving without you.")); 

    } 

  

    @Test 

    public void testDeleteMessageUsingAMessageHash() { 

        System.out.println("Test: Delete a message using a message hash"); 

        String generatedHash = Message.createMessageHash("1000000002", 2, "Where are you? You are late! I have asked you to be on time."); 

        String expectedResponse = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted."; 

        assertEquals(expectedResponse, Message.deleteByMessageHash(generatedHash)); 

    } 

  

    @Test 

    public void testDisplayReport() { 

        System.out.println("Test: Display Report"); 

        String report = Message.generateFullSystemReportText(); 

        assertNotNull(report); 

        assertTrue(report.contains("+27834557896")); 

    } 
    @Test 

    public void testCheckMessageLength_Success() { 
        String shortMessage = "Hi Mike, can you join us for dinner tonight?"; 
        assertEquals("Message ready to send.", Message.checkMessageLength(shortMessage)); 
    } 
    @Test 

    public void testCheckMessageLength_Failure() { 

        String longMessage = "a".repeat(255);  

        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", Message.checkMessageLength(longMessage)); 

    } 
    @Test 

    public void testCheckRecipientCell_Success() { 

        assertEquals("Cell phone number successfully captured.", Message.checkRecipientCell("+27718693002")); 

    } 
    @Test 

    public void testCheckRecipientCell_Failure() { 

        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", Message.checkRecipientCell("08575975889")); 

    } 
    @Test 

    public void testCheckMessageID() { 

        assertTrue(Message.checkMessageID("1234567890")); 

        assertFalse(Message.checkMessageID("12345678901")); 

    }  
}
