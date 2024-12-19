import java.util.*;

abstract class Product {
    String name;
    int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    abstract void display();
}

class Electronics extends Product {
    String brand;

    public Electronics(String name, int quantity, String brand) {
        super(name, quantity);
        this.brand = brand;
    }

    @Override
    void display() {
        System.out.println("Electronics - Name: " + name + ", Quantity: " + quantity + ", Brand: " + brand);
    }
}

class Food extends Product {
    String expiryDate;

    public Food(String name, int quantity, String expiryDate) {
        super(name, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    void display() {
        System.out.println("Food - Name: " + name + ", Quantity: " + quantity + ", Expiry Date: " + expiryDate);
    }
}

class StockManager {
    private final List<Product> products;

    public StockManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String name) {
        products.removeIf(product -> product.name.equalsIgnoreCase(name));
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                product.display();
            }
        }
    }

    public void searchProduct(String name) {
        for (Product product : products) {
            if (product.name.equalsIgnoreCase(name)) {
                product.display();
                return;
            }
        }
        System.out.println("Product not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        StockManager manager = new StockManager();

        // Sample data
        manager.addProduct(new Electronics("Laptop", 5, "Dell"));
        manager.addProduct(new Food("Apple", 20, "2024-12-31"));

        // Display menu
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Product\n2. Remove Product\n3. Display Products\n4. Search Product\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product type (1-Electronics, 2-Food): ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    if (type == 1) {
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        manager.addProduct(new Electronics(name, quantity, brand));
                    } else if (type == 2) {
                        System.out.print("Enter expiry date (yyyy-MM-dd): ");
                        String expiryDate = scanner.nextLine();
                        manager.addProduct(new Food(name, quantity, expiryDate));
                    } else {
                        System.out.println("Invalid product type.");
                    }
                    break;

                case 2:
                    System.out.print("Enter product name to remove: ");
                    String removeName = scanner.nextLine();
                    manager.removeProduct(removeName);
                    break;

                case 3:
                    manager.displayProducts();
                    break;

                case 4:
                    System.out.print("Enter product name to search: ");
                    String searchName = scanner.nextLine();
                    manager.searchProduct(searchName);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}