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

 import java.util.Random;  

import java.io.FileWriter;  

import java.io.IOException; 

public class Message { 

private static final int MAX_LIMIT = 100; 
 
private static String[] sentMessagesArray = new String[MAX_LIMIT]; 
private static String[] disregardedMessagesArray = new String[MAX_LIMIT]; 
private static String[] storedMessagesArray = new String[MAX_LIMIT]; 
private static String[] messageHashArray = new String[MAX_LIMIT]; 
private static String[] messageIdArray = new String[MAX_LIMIT]; 
private static String[] recipientArray = new String[MAX_LIMIT]; 
 
private static int sentCount = 0; 
private static int disregardCount = 0; 
private static int storedCount = 0; 
private static int totalMessagesSent = 0; 
 
public static boolean checkMessageID(String messageId) { 
   if (messageId == null) return false; 
   return messageId.length() <= 10; 
} 
 
public static String checkRecipientCell(String cellNumber) { 
   if (cellNumber != null && cellNumber.matches("^\\+27\\d{9}$")) { 
       return "Cell phone number successfully captured."; 
   } 
   return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again."; 
} 
 
public static String checkMessageLength(String messageText) { 
   if (messageText == null) return "Message is empty."; 
   if (messageText.length() <= 250) { 
       return "Message ready to send."; 
   } 
   int overrun = messageText.length() - 250; 
   return "Message exceeds 250 characters by " + overrun + "; please reduce the size."; 
} 
 
public static String createMessageHash(String messageId, int currentMsgNum, String messageText) { 
   String firstTwoId = messageId.substring(0, 2); 
   String[] words = messageText.trim().split("\\s+"); 
   String firstWord = words[0]; 
   String lastWord = words[words.length - 1]; 
   return (firstTwoId + ":" + currentMsgNum + ":" + firstWord + lastWord).toUpperCase(); 
} 
 
public static String SentMessage(Scanner input, String messageId, String recipient, String messageText) { 
   System.out.println("\nMessage completed. What would you like to do?"); 
   System.out.println("1) Send Message"); 
   System.out.println("2) Disregard Message"); 
   System.out.println("3) Store Message to send later"); 
   System.out.print("Choose an option: "); 
    
   int subChoice = input.nextInt(); 
   input.nextLine();  
 
   String hash = createMessageHash(messageId, totalMessagesSent + 1, messageText); 
 
   if (subChoice == 1) { 
       totalMessagesSent++; 
        
       sentMessagesArray[sentCount] = messageText; 
       messageHashArray[sentCount] = hash; 
       messageIdArray[sentCount] = messageId; 
       recipientArray[sentCount] = recipient; 
       sentCount++; 
 
       String messageDetails = "---------------------------------\n" + 
                               "Message ID: " + messageId + "\n" + 
                               "Message Hash: " + hash + "\n" + 
                               "Recipient: " + recipient + "\n" + 
                               "Message: " + messageText + "\n" + 
                               "---------------------------------\n"; 
        
       System.out.println("\nMessage successfully sent."); 
       System.out.print(messageDetails); 
       return "Message successfully sent."; 
 
   } else if (subChoice == 2) { 
       System.out.println("Press 0 to delete the message"); 
       String confirmDelete = input.nextLine(); 
       if (confirmDelete.equals("0")) { 
           disregardedMessagesArray[disregardCount] = messageText; 
           disregardCount++; 
           System.out.println("Message deleted successfully."); 
           return "Press 0 to delete the message."; 
       } 
       return "Deletion canceled."; 
 
   } else if (subChoice == 3) { 
       storedMessagesArray[storedCount] = messageText; 
       int trackingIndex = sentCount + storedCount; 
       messageIdArray[trackingIndex] = messageId;  
       messageHashArray[trackingIndex] = hash; 
       recipientArray[trackingIndex] = recipient; 
       storedCount++; 
 
       storeMessage(messageId, hash, recipient, messageText); 
       System.out.println("Message successfully stored."); 
       return "Message successfully stored."; 
   } 
    
   return "Failed"; 
} 
 
public static void populateTestDataDirectly(String messageId, String recipient, String messageText, String flag) { 
   String hash = createMessageHash(messageId, totalMessagesSent + 1, messageText); 
   if (flag.equalsIgnoreCase("Sent")) { 
       totalMessagesSent++; 
       sentMessagesArray[sentCount] = messageText; 
       messageHashArray[sentCount] = hash; 
       messageIdArray[sentCount] = messageId; 
       recipientArray[sentCount] = recipient; 
       sentCount++; 
   } else if (flag.equalsIgnoreCase("Stored")) { 
       storedMessagesArray[storedCount] = messageText; 
       int idx = sentCount + storedCount; 
       messageIdArray[idx] = messageId; 
       messageHashArray[idx] = hash; 
       recipientArray[idx] = recipient; 
       storedCount++; 
       storeMessage(messageId, hash, recipient, messageText); 
   } else if (flag.equalsIgnoreCase("Disregard")) { 
       disregardedMessagesArray[disregardCount] = messageText; 
       disregardCount++; 
   } 
} 
 
public static void clearArraysForTesting() { 
   sentCount = 0; 
   disregardCount = 0; 
   storedCount = 0; 
   totalMessagesSent = 0; 
   sentMessagesArray = new String[MAX_LIMIT]; 
   disregardedMessagesArray = new String[MAX_LIMIT]; 
   storedMessagesArray = new String[MAX_LIMIT]; 
   messageHashArray = new String[MAX_LIMIT]; 
   messageIdArray = new String[MAX_LIMIT]; 
   recipientArray = new String[MAX_LIMIT]; 
} 
 
public static String getSentMessagesListReport() { 
   if (sentCount == 0) return ""; 
   StringBuilder sb = new StringBuilder(); 
   for (int i = 0; i < sentCount; i++) { 
       sb.append(sentMessagesArray[i]); 
       if (i < sentCount - 1) sb.append(", "); 
   } 
   return sb.toString(); 
} 
 
public static String getLongestStoredMessageText() { 
   if (storedCount == 0) return "No messages stored."; 
   String longest = ""; 
   for (int i = 0; i < storedCount; i++) { 
       if (storedMessagesArray[i] != null && storedMessagesArray[i].length() > longest.length()) { 
           longest = storedMessagesArray[i]; 
       } 
   } 
   return longest; 
} 
 
public static String searchByMessageID(String searchId) { 
   int limit = sentCount + storedCount + 1; 
   for (int i = 0; i < limit; i++) { 
       if (messageIdArray[i] != null && messageIdArray[i].equals(searchId)) { 
           String content = (i < sentCount) ? sentMessagesArray[i] : storedMessagesArray[i - sentCount - 1]; 
           return content; 
       } 
   } 
   return "Message ID not found."; 
} 
 
public static String searchAllByRecipient(String targetRecipient) { 
   StringBuilder sb = new StringBuilder(); 
   int totalLimit = sentCount + storedCount + 1; 
   for (int i = 0; i < totalLimit; i++) { 
       if (recipientArray[i] != null && recipientArray[i].equals(targetRecipient)) { 
           String content = (sentMessagesArray[i] != null) ? sentMessagesArray[i] : storedMessagesArray[i - sentCount - 1]; 
           if (sb.length() > 0) sb.append(" "); 
           sb.append(content); 
       } 
   } 
   return sb.toString(); 
} 
 
public static String deleteByMessageHash(String targetHash) { 
   int maxSearch = sentCount + storedCount + 1; 
   for (int i = 0; i < maxSearch; i++) { 
       if (messageHashArray[i] != null && messageHashArray[i].equalsIgnoreCase(targetHash)) { 
           String content = (sentMessagesArray[i] != null) ? sentMessagesArray[i] : storedMessagesArray[i - sentCount - 1]; 
           messageHashArray[i] = null; 
           if (sentMessagesArray[i] != null) sentMessagesArray[i] = null; 
           else storedMessagesArray[i - sentCount - 1] = null; 
           return "Message: \"" + content + "\" successfully deleted."; 
       } 
   } 
   return "Hash not found."; 
} 
 
public static String generateFullSystemReportText() { 
   StringBuilder sb = new StringBuilder(); 
   for (int i = 0; i < sentCount; i++) { 
       if (messageHashArray[i] != null) { 
           sb.append("Hash: ").append(messageHashArray[i]) 
             .append(" Recipient: ").append(recipientArray[i]) 
             .append(" Message: ").append(sentMessagesArray[i]).append("\n"); 
       } 
   } 
   return sb.toString(); 
} 
 
public static String printMessages() { 
   if (sentCount == 0) { 
       return "No messages sent during this session yet."; 
   } 
   StringBuilder report = new StringBuilder(); 
   for (int i = 0; i < sentCount; i++) { 
       report.append("Message ID: ").append(messageIdArray[i]).append("\n") 
             .append("Hash: ").append(messageHashArray[i]).append("\n") 
             .append("Recipient: ").append(recipientArray[i]).append("\n") 
             .append("Message: ").append(sentMessagesArray[i]).append("\n") 
             .append("---------------------------------\n"); 
   } 
   return report.toString(); 
} 
 
public static int returnTotalMessagess() { 
   return totalMessagesSent; 
} 
 
public static void storeMessage(String messageId, String hash, String recipient, String messageText) { 
   String jsonRecord = "{\n" + 
           "  \"messageId\": \"" + messageId + "\",\n" + 
           "  \"messageHash\": \"" + hash + "\",\n" + 
           "  \"recipient\": \"" + recipient + "\",\n" + 
           "  \"message\": \"" + messageText.replace("\"", "\\\"") + "\"\n" + 
           "},\n"; 
 
   try (FileWriter writer = new FileWriter("messages.json", true)) { 
       writer.write(jsonRecord); 
   } catch (IOException e) { 
       System.out.println("Error saving to JSON file: " + e.getMessage()); 
   } 
} 
 
public static void handleStoredMessagesMenu(Scanner input) { 
   while (true) { 
       System.out.println("\n--- Stored Messages Sub-Menu ---"); 
       System.out.println("a) Display sender and recipient of all stored messages"); 
       System.out.println("b) Display the longest stored message"); 
       System.out.println("c) Search for a message ID and display tracking information"); 
       System.out.println("d) Search for all messages stored for a particular recipient"); 
       System.out.println("e) Delete a message using the message hash"); 
       System.out.println("f) Display a report that lists the full details of all stored messages"); 
       System.out.println("g) Return to Main Menu"); 
       System.out.print("Choose an option (a-g): "); 
        
       String opt = input.nextLine().trim().toLowerCase(); 
       if (opt.equals("g")) break; 
 
       switch (opt) { 
           case "a": 
               System.out.println("\n--- Sender & Recipient of Stored Messages ---"); 
               if (storedCount == 0) { 
                   System.out.println("No messages stored."); 
               } else { 
                   for (int i = 0; i < storedCount; i++) { 
                       System.out.println("Stored Slot " + (i + 1) + " -> Recipient Cell: " + recipientArray[sentCount + i]); 
                   } 
               } 
               break; 
 
           case "b": 
               System.out.println("\n--- Longest Stored Message ---"); 
               System.out.println(getLongestStoredMessageText()); 
               break; 
 
           case "c": 
               System.out.print("Enter Message ID to search: "); 
               String searchId = input.nextLine().trim(); 
               System.out.println(searchByMessageID(searchId)); 
               break; 
 
           case "d": 
               System.out.print("Enter Recipient Cell Number (+27...): "); 
               String searchRecipient = input.nextLine().trim(); 
               System.out.println(searchAllByRecipient(searchRecipient)); 
               break; 
 
           case "e": 
               System.out.print("Enter Message Hash to delete: "); 
               String searchHash = input.nextLine().trim(); 
               System.out.println(deleteByMessageHash(searchHash)); 
               break; 
 
           case "f": 
               System.out.println("\n--- Full Structural System Report ---"); 
               System.out.println(generateFullSystemReportText()); 
               break; 
 
           default: 
               System.out.println("Invalid selection. Choose between options a and g."); 
       } 
   } 
} 
 
public static void startMessageSession(Scanner input) { 
   Random rand = new Random(); 
 
   System.out.print("How many messages do you wish to enter? "); 
   int totalSessionLimit = input.nextInt(); 
   input.nextLine();  
    
   int messagesProcessedThisSession = 0; 
   boolean running = true; 
 
   while (running) { 
       System.out.println("\n--- QuickChat Menu ---"); 
       System.out.println("1) Send Messages"); 
       System.out.println("2) Show recently sent messages"); 
       System.out.println("3) Quit"); 
       System.out.println("4) Stored Messages (Data & Reports Options)"); 
       System.out.print("Choose an option: "); 
        
       int choice = input.nextInt(); 
       input.nextLine();  
 
       switch (choice) { 
           case 1: 
               if (messagesProcessedThisSession >= totalSessionLimit) { 
                   System.out.println("Limit reached! You cannot enter more than " + totalSessionLimit + " messages."); 
                   break; 
               } 
 
               while (messagesProcessedThisSession < totalSessionLimit) { 
                   System.out.print("\nEnter recipient cell number: "); 
                   String recipient = input.nextLine(); 
 
                   String validationResult = checkRecipientCell(recipient); 
                   if (!validationResult.equals("Cell phone number successfully captured.")) { 
                       System.out.println(validationResult); 
                       continue;  
                   } 
 
                   System.out.print("Enter message: "); 
                   String messageText = input.nextLine(); 
 
                   String lengthResult = checkMessageLength(messageText); 
                   if (!lengthResult.equals("Message ready to send.")) { 
                       System.out.println(lengthResult); 
                       continue; 
                   } 
 
                   long rawTenDigit = 1000000000L + (long)(rand.nextDouble() * 9000000000L); 
                   String uniqueMessageID = String.valueOf(rawTenDigit); 
 
                   String outcome = SentMessage(input, uniqueMessageID, recipient, messageText); 
                    
                   if (outcome.equals("Message successfully sent.") || outcome.equals("Message successfully stored.")) { 
                       messagesProcessedThisSession++; 
                   } 
 
                   if (messagesProcessedThisSession < totalSessionLimit) { 
                       System.out.print("\nDo you want to type your next message now? (yes/no): "); 
                       String nextMove = input.nextLine(); 
                       if (nextMove.equalsIgnoreCase("no")) { 
                           break;  
                       } 
                   } 
               } 
 
               if (messagesProcessedThisSession >= totalSessionLimit) { 
                   System.out.println("\n============================================="); 
                   System.out.println("All allocated messages for this session processed."); 
                   System.out.println("Total lifetime system messages accumulated: " + returnTotalMessagess()); 
                   System.out.println("============================================="); 
               } 
               break; 
 
           case 2: 
               System.out.println("\n--- Recently Sent Messages ---"); 
               System.out.println(printMessages()); 
               break; 
 
           case 3: 
               System.out.println("Goodbye!"); 
               running = false; 
               break; 
 
           case 4: 
               handleStoredMessagesMenu(input); 
               break; 
 
           default: 
               System.out.println("Invalid menu choice. Try again."); 
       } 
   } 
} 
 

} 
