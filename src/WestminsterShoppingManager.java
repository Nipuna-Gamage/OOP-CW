import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

//The WestminsterShoppingManager class manages the shopping system.
//It allows users to add, delete, print, and save products, as well as interact with the shopping GUI.
//Managers can also log in to perform administrative actions.
public class WestminsterShoppingManager implements ShoppingManager {
    List<Product> producList = new ArrayList<>();// List to store the available products.
    private List<Product> ShopCart; // List to store the products added to the shopping cart.
    private List<User> userList = new ArrayList<>();//user array list
    private static final int Max_P = 50;// Maximum number of products allowed.
    //Gets the total number of products in the product list.
    private int getTotalProducts() {
        return producList.size();
    }
    public List<Product> getproducList(){return producList;} //Retrieves the list of available products.
    public List<Product> getShoppingList() {
        return ShopCart;
    }
    @Override
    public void addProduct() { // Implementation for adding a new product...
        if (getTotalProducts() >= Max_P) {
            System.out.println("Maximum number of products reached. Cannot add more products.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        String productID;
        do {
            System.out.print("Enter product ID : ");// get product ID
            productID = scanner.nextLine().strip();
            if (productID.isEmpty()) {
                System.out.println("Product ID cannot be empty. Please enter a valid ID.");
            } else {
                break;
            }
        } while (true);
        String productName;
        do {
            System.out.print("Enter product name : ");// get product name
            productName = scanner.nextLine().strip();
            if (productName.isEmpty()) {
                System.out.println("product name cannot be empty. Please enter a product name.");
            } else {
                break;
            }
        } while (true);
        int availableItems;// Get a valid number of available items
        do {
            System.out.print("Enter number of available items: ");
            String availableItemsInput = scanner.nextLine().strip();
            try {
                availableItems = Integer.parseInt(availableItemsInput);
                if (availableItems >= 0) {
                    break; // Valid number of available items provided, exit the loop
                } else {
                    System.out.println("Number of available items cannot be negative. Please enter a valid value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the number of available items.");
            }
        } while (true);
        double price;// Get a valid product price
        do {
            System.out.print("Enter price: ");
            String priceInput = scanner.nextLine().strip();
            try {
                price = Double.parseDouble(priceInput);
                if (price >= 0) {
                    break;// Valid price provided, exit the loop
                } else {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the price.");
            }
        } while (true);
        System.out.print("Select product type [Electronics(E) or Clothing(C)] : ");
        String productType = scanner.nextLine().strip();//get product type
        if (("E".equalsIgnoreCase(productType))) {
            addElectronics(productID, productName, availableItems, price);
        } else if ("C".equalsIgnoreCase(productType)) {
            addClothing(productID, productName, availableItems, price);
        } else {
            System.out.println("Invalid choice....");
        }
    }
    private void addElectronics(String productID, String productName, int availableItems, double price) {
        Scanner scanner = new Scanner(System.in);
        String brand;
        do {
            System.out.print("Enter the brand: ");// get product name
            brand = scanner.nextLine().strip();
            if (brand.isEmpty()) {
                System.out.println("Brand name cannot be empty. Please enter a brand name.");
            } else {
                break;
            }
        } while (true);
        int warrantyPeriod;// Get a valid warranty period
        do {
            System.out.print("Enter the warranty period (in weeks): ");
            String warrantyPeriodInput = scanner.nextLine().strip();
            try {
                warrantyPeriod = Integer.parseInt(warrantyPeriodInput);
                if (warrantyPeriod > 0) {
                    break; // Valid warranty period provided, exit the loop
                } else {
                    System.out.println("Warranty period must be a positive integer. Please enter a valid value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the warranty period.");
            }
        } while (true);
        Electronics electronicProduct = new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod);
        producList.add(electronicProduct);
        System.out.println("Electronics added successfully.");
    }
    private void addClothing(String productID, String productName, int availableItems, double price) {
        Scanner scanner = new Scanner(System.in);
        String size;
        do {
            System.out.print("Enter the size: ");// get Clothing size
            size = scanner.nextLine().strip();
            if (size.isEmpty()) {
                System.out.println("Clothing size cannot be empty. Please enter a Clothing size.");
            } else {
                break;
            }
        } while (true);
        String color;
        do {
            System.out.print("Enter the color: ");// get Clothing color
            color = scanner.nextLine().strip();
            if (color.isEmpty()) {
                System.out.println("Clothing color cannot be empty. Please enter a Clothing color.");
            } else {
                break;
            }
        } while (true);
        Clothing clothingProduct = new Clothing(productID, productName, availableItems, price, size, color);
        producList.add(clothingProduct);
        System.out.println("Clothing added successfully.");
    }
    @Override
    public void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to delete: ");
        String productIDToDelete = scanner.nextLine().strip();
        Product productToDelete = null;
        for (Product product : producList) {
            if (product.getProductID().equals(productIDToDelete)) {
                productToDelete = product;
                break;
            }
        }
        if (productToDelete != null) {
            producList.remove(productToDelete);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product with ID " + productIDToDelete + " not found.");
        }
    }
    @Override
    public void printProducts() {
        if (producList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("\nList of Products:");
        for (Product product : producList) {
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Available Items: " + product.getAvailableItems());
            System.out.println("Price: " + product.getPrice());
            // Check the product type and display relevant information
            if (product instanceof Electronics) {
                Electronics electronicProduct = (Electronics) product;
                System.out.println("Brand: " + electronicProduct.getBrand());
                System.out.println("Warranty Period: " + electronicProduct.getWarrantyPeriod());
                System.out.println("Product Type: Electronics");
            } else if (product instanceof Clothing) {
                Clothing clothingProduct = (Clothing) product;
                System.out.println("Size: " + clothingProduct.getSize());
                System.out.println("Color: " + clothingProduct.getColor());
                System.out.println("Product Type: Clothing");
            }
            System.out.println();
        }
    }
    @Override
    public void saveProducts() {
        if (producList.isEmpty()) {
            System.out.println("No products available to save.");
            return;
        }
        try (FileWriter fileWriter = new FileWriter("products.txt")) {
            fileWriter.append("");
            for (Product product : producList) {
                fileWriter.append(product.getProductID()).append(",");
                fileWriter.append(product.getProductName()).append(",");
                fileWriter.append(Integer.toString(product.getAvailableItems())).append(",");
                fileWriter.append(Double.toString(product.getPrice())).append(",");
                if (product instanceof Electronics) {
                    fileWriter.append("Electronics,");
                    Electronics electronicProduct = (Electronics) product;
                    fileWriter.append(electronicProduct.getBrand()).append(",");
                    fileWriter.append(Integer.toString(electronicProduct.getWarrantyPeriod()));
                } else if (product instanceof Clothing) {
                    fileWriter.append("Clothing,");
                    Clothing clothingProduct = (Clothing) product;
                    fileWriter.append(clothingProduct.getSize()).append(",");
                    fileWriter.append(clothingProduct.getColor());
                }
                fileWriter.append("\n");
            }
            System.out.println("Products saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
        }
    }
    public void loadProducts() {//Loads product data from a file.
        try (BufferedReader reader = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String productID = parts[0];
                String productName = parts[1];
                int availableItems = Integer.parseInt(parts[2]);
                double price = Double.parseDouble(parts[3]);
                String productType = parts[4];
                Product product;
                if ("Electronics".equals(productType)) {
                    String brand = parts[5];
                    int warrantyPeriod = Integer.parseInt(parts[6]);
                    product = new Electronics(productID, productName, availableItems, price, brand, warrantyPeriod);
                    producList.add(product);
                } else if ("Clothing".equals(productType)) {
                    String size = parts[5];
                    String color = parts[6];
                    product = new Clothing(productID, productName, availableItems, price, size, color);
                    producList.add(product);
                } else {
                    System.out.println("Unsupported product type: " + productType);
                }
            }
            System.out.println("\nProducts loaded successfully.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("\nError loading products: " + e.getMessage());
        }
    }
    public static void openGUI() { //Opens the shopping GUI using Swing.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
                shoppingManager.loadProducts(); // Load products from file when the application starts
                ShopGUI shoppingGUI = new ShopGUI(shoppingManager.getproducList());
                shoppingGUI.setVisible(true);
            }
        });
        System.out.println("Opening GUI...");
    }
    public void logIn() { //Logs in a user based on provided credentials.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine().strip();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine().strip();
        User loggedInUser = findUser(username, password);
        if (loggedInUser != null){
            System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + ".");
            openGUI();
        }else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }
    User findUser(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // User not found
    }
    public void register() { //Registers a new user.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a new username: ");
        String newUsername = scanner.nextLine().strip();
        if (isUsernameTaken(newUsername)) {
            System.out.println("Username already taken. Please choose a different one.");
            return;
        }
        System.out.println("Enter a new password: ");
        String newPassword = scanner.nextLine().strip();
        User newUser = new User(newUsername, newPassword);
        userList.add(newUser);
        saveUserToFile(newUser);
        System.out.println("Registration successful! Welcome, " + newUsername + ".");
    }
    private boolean isUsernameTaken(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    private void loadUsersFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    User user = new User(username, password);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }
    public void saveUserToFile(User user) {
        try (FileWriter fileWriter = new FileWriter("users.txt", true)) {
            fileWriter.append(user.getUsername()).append(",").append(user.getPassword()).append("\n");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }
    public static void displayMenu2() {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("\t\t--- User Menu ---");
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Back");
        System.out.println("0. Exit");
    }
    public void UserPress() {
        boolean back = false;
        while (!back) {
            displayMenu2();
            Scanner scanner = new Scanner(System.in);
            System.out.print("What do you wanna do?");
            String choice = scanner.nextLine().strip();
            switch (choice) {
                case "1":
                    logIn();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    back = true;
                    break;
                case "0":
                    System.out.println("Exiting The Program.......");
                    System.exit(0); //Exit the Program.
                default:
                    System.out.println("Invalid choice. Try again.....");
            }
        }
    }
    private boolean isLoggedIn = false;
    public void managerPress() {
        Scanner scanner = new Scanner(System.in);
        while (!isLoggedIn) {
            System.out.print("\nEnter manager username: ");
            String enteredUsername = scanner.nextLine().strip();
            System.out.print("Enter manager password: ");
            String enteredPassword = scanner.nextLine().strip();
            if (validateCredentials(enteredUsername, enteredPassword)) {
                System.out.println("Login successful! Welcome, " + enteredUsername + ".");
                isLoggedIn = true;
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }
    private boolean validateCredentials(String enteredUsername, String enteredPassword) {
        return "admin".equals(enteredUsername) && "admin".equals(enteredPassword);
    }
    public static void displayMenu1() {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("\t\t--- Shopping System ---");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print products");
        System.out.println("4. Save products");
        System.out.println("5. open GUI");
        System.out.println("6. Back");
        System.out.println("0. Exit");
    }
    public void Managerpress() {
        WestminsterShoppingManager s1 = new WestminsterShoppingManager();
        s1.loadProducts();
        s1.managerPress();
        boolean back = false;
        while (!back) {
            displayMenu1();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().strip();
            switch (choice) {
                case "1":
                    s1.addProduct();
                    break;
                case "2":
                    s1.deleteProduct();
                    break;
                case "3":
                    s1.printProducts();
                    break;
                case "4":
                    s1.saveProducts();
                    break;
                case "5":
                    openGUI();
                    break;
                case "6":
                    back = true;
                    break;
                case "0":
                    System.out.println("Exiting The Program.......");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.....");
            }
        }
    }
    public static void displayMenu() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("\t\t--- Menu ---");
        System.out.println("1. User ");
        System.out.println("2. Manager ");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) { //Main method to run the shopping program.
        WestminsterShoppingManager s1 = new WestminsterShoppingManager();
        s1.loadProducts();
        s1.loadUsersFile();
        // Main program loop
        while (true) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().strip();
            switch (choice) {
                case "1":
                    s1.UserPress();
                    break;
                case "2":
                    s1.Managerpress();
                    break;
                case "0":
                    System.out.println("Exiting The Program.......");
                    System.exit(0); //Exit the Program.
                default:
                    System.out.println("Invalid choice. Try again.....");
            }
        }
    }
}