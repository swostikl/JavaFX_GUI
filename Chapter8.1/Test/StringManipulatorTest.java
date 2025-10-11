import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorTest {
    private StringManipulator manipulator;

    @BeforeEach
    void setUp() {
        manipulator = new StringManipulator();
    }

    @Test
    @DisplayName("Test concatenation of two strings")
    void testConcatenate() {
        String result = manipulator.concatenate("Hello", "World");
        assertEquals("HelloWorld", result, "Concatenation failed");

        result = manipulator.concatenate("", "World");
        assertEquals("World", result, "Concatenation with empty string failed");

    }

    @Test
    @DisplayName("Test finding length of a string")
    void testFindLength() {
        assertEquals(5, manipulator.findLength("Hello"), "Incorrect length for 'Hello'");
        assertEquals(0, manipulator.findLength(""), "Incorrect length for empty string");

    }

    @Test
    @DisplayName("Test converting string to upperrcase")
    void testConvertToUpperCase() {
        assertEquals("HELLO", manipulator.convertToUpperCase("hello"), "Uppercase conversion failed");
        assertEquals("HELLO", manipulator.convertToUpperCase("Hello"), "Uppercase conversion failed");
    }



    @Test
    @DisplayName("Test converting string to lowercase")
    void testConvertToLowerCase() {
        assertEquals("hello", manipulator.convertToLowerCase("HELLO"), "Lowercase conversion failed");
        assertEquals("hello", manipulator.convertToLowerCase("Hello"), "Lowercase conversion failed");

    }

    @Test
    @DisplayName("Test substring containment")
    void testContainsSubstring() {
        assertTrue(manipulator.containsSubstring("HelloWorld", "World"), "Substring detection failed");
        assertFalse(manipulator.containsSubstring("HelloWorld", "Earth"), "Substring detection should fail");
        assertTrue(manipulator.containsSubstring("Apple", "pp"), "Substring detection failed");

    }
}