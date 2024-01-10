package test.java;

import main.java.TopNDomainsReadBased;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopNDomainsReadBasedTest {

    @Test
    void testTopNDomainsReadBased() {
        // Set up test input
        String testInput = "user1@example.com\nuser2@example.com\nuser3@example.com\n";

        // Redirect System.in and System.out for testing
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8)));

        // Redirect System.out for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method
        TopNDomainsReadBased.main(new String[]{});

        // Restore System.in and System.out
        System.setIn(originalSystemIn);
        System.setOut(System.out);

        // Verify the output
        String expectedOutput = "example.com 3\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testExtractDomain() {
        assertEquals("example.com", TopNDomainsReadBased.extractDomain("user@example.com"));
        assertEquals("", TopNDomainsReadBased.extractDomain("invalid-email"));
    }

}