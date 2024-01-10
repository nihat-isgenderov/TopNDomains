package test.java;

import main.java.TopNDomainWriteBased;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TopEmailDomainsTest {

    @Test
    public void testTopEmailDomains() {
        // Redirect System.in and System.out for testing
        String input = "joeblogs@saldoaaps.com\nandrew.smith@gmail.com\nyahoo@domain.com\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);

        // Redirect System.out for testing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the program
        TopNDomainWriteBased.main(new String[]{});

        // Restore System.in and System.out
        System.setIn(System.in);

        // Restore System.out
        System.setOut(System.out);

        // Check the output
        String expectedOutput = "saldoaaps.com 1\n" +
                "gmail.com 1\n" +
                "domain.com 1\n" ;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testExtractDomain() {
        assertEquals("saldoaaps.com", TopNDomainWriteBased.extractDomain("joeblogs@saldoaaps.com"));
        assertEquals("gmail.com", TopNDomainWriteBased.extractDomain("andrew.smith@gmail.com"));
        assertEquals("", TopNDomainWriteBased.extractDomain("invalid-email"));
    }

    @Test
    public void testPrintTopDomains() {
        Map<String, Integer> domainCounts = new HashMap<>();
        domainCounts.put("com", 5);
        domainCounts.put("org", 3);
        domainCounts.put("net", 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        TopNDomainWriteBased.printTopDomains(domainCounts, 2);

        System.setOut(System.out);

        String expectedOutput = "com 5\n" +
                "org 3\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
