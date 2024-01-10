package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


public class TopNDomainsReadBased {
    private final static int topNDomains = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Map to store domain counts
        Map<String, Integer> domainCounts = new HashMap<>();
        // Use a PriorityQueue to find the top N domains
        Queue<DomainCount> priorityQueue = new PriorityQueue<>((dc1, dc2) -> dc2.count() - dc1.count());

        // Read each line and extract domain
        while (scanner.hasNextLine()) {
            String email = scanner.nextLine().trim();
            String domain = extractDomain(email);
            Integer count = domainCounts.merge(domain, 1, Integer::sum);
            priorityQueue.offer(new DomainCount(email, count));
        }
        scanner.close();

        // Populate the PriorityQueue with DomainCount objects
        for (Map.Entry<String, Integer> entry : domainCounts.entrySet()) {
            priorityQueue.offer(new DomainCount(entry.getKey(), entry.getValue()));
        }

        // Print the top N domains
        printTopDomains(priorityQueue, topNDomains);
    }

    public static String extractDomain(String email) {
        // Split email by '@' and take the second part
        String[] parts = email.split("@");
        return parts.length == 2 ? parts[1] : "";
    }

    public static void printTopDomains(Queue<DomainCount> priorityQueue, int n) {
        // Print the top N domains
        for (int i = 0; i < n && !priorityQueue.isEmpty(); i++) {
            DomainCount domainCount = priorityQueue.poll();
            System.out.println(domainCount.domain() + " " + domainCount.count());
        }
    }

    // Helper class to represent domain and count
        public record DomainCount(String domain, int count) {
    }
}
