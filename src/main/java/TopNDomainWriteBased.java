package main.java;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Top N domains
 * Regarding optimality:
 *
 * Space Complexity: The program uses a HashMap to store domain counts.
 * The space complexity is O(D), where D is the number of unique domains.
 *
 * Time Complexity: The time complexity is O(N log N), where N is the number of email addresses.
 * The program sorts the domain counts, and in the worst case, it would be O(D log D), where D is the number of unique domains.
 *
 * This solution is efficient in terms of both time and space complexity for the given task.
 * It uses a map to count domain occurrences and sorts the results to print the top domains,
 * ensuring a reasonable performance for typical input sizes.
 *
 * Time complexity for write operation happens in O(1).
 */
public class TopNDomainWriteBased {

    public static void main(String[] args) {
        // Read input from standard input
        Scanner scanner = new Scanner(System.in);

        // Map to store domain counts
        Map<String, Integer> domainCounts = new ConcurrentHashMap<>();

        // Read each line and extract domain
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String domain = extractDomain(email);
            domainCounts.put(domain, domainCounts.getOrDefault(domain, 0) + 1);
        }

        // Close the scanner
        scanner.close();

        // Print the top 10 domains
        printTopDomains(domainCounts, 10);
    }

    public static String extractDomain(String email) {
        // Split email by '@' and take the second part
        String[] parts = email.split("@");
        if (parts.length == 2) {
            return parts[1];
        } else {
            // If the email doesn't follow the expected format, return an empty string
            return "";
        }
    }

    public static void printTopDomains(Map<String, Integer> domainCounts, int n) {
        // Sort the domains by count in descending order
        domainCounts.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(n)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}
