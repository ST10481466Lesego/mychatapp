package com.mycompany.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatappTest {

    @Test
    public void testCheckUserName_Valid() {
        // Valid: Under 5 chars and contains underscore
        assertTrue(Chatapp.checkUserName("ky_le"), "Valid username should return true");
    }

    @Test
    public void testCheckUserName_Invalid() {
        // Invalid: Too long or no underscore
        assertFalse(Chatapp.checkUserName("kyle_longname"), "Long username should return false");
        assertFalse(Chatapp.checkUserName("kyle"), "Username without underscore should return false");
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        // Valid: 8+ chars, Capital, Number, Special
        assertTrue(Chatapp.checkPasswordComplexity("P@ssword1"), "Complex password should return true");
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        // Invalid: Missing capital or special character
        assertFalse(Chatapp.checkPasswordComplexity("password1"), "Missing capital should return false");
        assertFalse(Chatapp.checkPasswordComplexity("Password"), "Missing special/number should return false");
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        // Valid: +27 followed by 9 digits
        assertTrue(Chatapp.checkCellPhoneNumber("+27123456789"), "Correct format should return true");
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange inputs that we know pass all individual checks
        String user = "ky_le";
        String pass = "P@ssword1";
        String cell = "+27123456789";
        
        // Act
        String result = Chatapp.registerUser(user, pass, cell);
        
        // Assert: The string should contain "Registration successful"
        assertTrue(result.contains("Registration successful"), "Expected successful registration message");
    }
}