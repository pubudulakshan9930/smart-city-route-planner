import java.util.Scanner;

public class SmartCityRoutePlanner {
    public static void main(String[] args) {
        Graph graph = new Graph();
        AVLTree avl = new AVLTree();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Smart City Route Planner!");

        do {
            System.out.println("\n--- Smart City Route Planner ---");
            System.out.println("1. Add a new location");
            System.out.println("2. Remove a location");
            System.out.println("3. Add a road between locations");
            System.out.println("4. Remove a road");
            System.out.println("5. Display all connections");
            System.out.println("6. Display all locations (using a tree)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // Input validation for choice
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-7).");
                scanner.next(); // Discard invalid input
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter location name: ");
                    String locAdd = scanner.nextLine().trim();
                    if (!locAdd.isEmpty()) {
                        avl.insert(locAdd, graph);
                    } else {
                        System.out.println("Invalid location name.");
                    }
                    break;
                case 2:
                    System.out.print("Enter location name to remove: ");
                    String locRemove = scanner.nextLine().trim();
                    if (!locRemove.isEmpty() && avl.contains(locRemove)) {
                        avl.delete(locRemove, graph);
                    } else {
                        System.out.println("Location not found or invalid name.");
                    }
                    break;
                case 3:
                    System.out.print("Enter first location: ");
                    String loc1 = scanner.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String loc2 = scanner.nextLine().trim();
                    if (!loc1.isEmpty() && !loc2.isEmpty()) {
                        graph.addRoad(loc1, loc2);
                    } else {
                        System.out.println("Invalid location names.");
                    }
                    break;
                case 4:
                    System.out.print("Enter first location: ");
                    String loc3 = scanner.nextLine().trim();
                    System.out.print("Enter second location: ");
                    String loc4 = scanner.nextLine().trim();
                    if (!loc3.isEmpty() && !loc4.isEmpty()) {
                        graph.removeRoad(loc3, loc4);
                    } else {
                        System.out.println("Invalid location names.");
                    }
                    break;
                case 5:
                    graph.displayConnections();
                    break;
                case 6:
                    avl.displayLocations();
                    break;
                case 7:
                    System.out.println("Exiting Smart City Route Planner. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-7.");
            }
        } while (choice != 7);

        scanner.close();
    }
}