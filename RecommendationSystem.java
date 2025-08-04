package AIBasedRecommendationSystem;


import java.io.*;
import java.util.*;

public class RecommendationSystem {

    private static final String FILE_NAME = "items.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = loadItemsFromFile();

        if (items.isEmpty()) {
            System.out.println("❌ No items found to recommend.");
            return;
        }

        System.out.print("🧠 Enter your interest (e.g., sci-fi, programming, fantasy): ");
        String interest = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n🎯 Recommendations based on your interest:");
        boolean found = false;
        for (Item item : items) {
            if (item.category.toLowerCase().contains(interest)) {
                System.out.println("✅ " + item.name + " [" + item.category + "]");
                found = true;
            }
        }

        if (!found) {
            System.out.println("🚫 Sorry, no matches found.");
        }

        scanner.close();
    }

    private static List<Item> loadItemsFromFile() {
        List<Item> itemList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    itemList.add(new Item(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Error reading items file: " + e.getMessage());
        }
        return itemList;
    }

    static class Item {
        String name;
        String category;

        Item(String name, String category) {
            this.name = name;
            this.category = category;
        }
    }
}
