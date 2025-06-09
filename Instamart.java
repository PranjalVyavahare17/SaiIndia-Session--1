import java.util.*;

class Product {
    int id;
    String name;
    String category;
    double price;
    int stock;

    public Product(int id, String name, String category, double price, int stock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return id + ". " + name + " (" + category + ") - Rs. " + price + " [Stock: " + stock + "]";
    }
}

class Cart {
    Map<Product, Integer> items = new HashMap<>();

    void addProduct(Product product, int quantity) {
        if (product.stock < quantity) {
            System.out.println("Not enough stock.");
            return;
        }
        items.put(product, items.getOrDefault(product, 0) + quantity);
        product.stock -= quantity;
        System.out.println("Added to cart.");
    }

    void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int q = entry.getValue();
            System.out.println(p.name + " x " + q + " = Rs. " + (p.price * q));
            total += p.price * q;
        }
        System.out.println("Total: Rs. " + total);
    }
}

class User {
    String name;
    Cart cart = new Cart();

    public User(String name) {
        this.name = name;
    }
}

public class InstamartApp {
    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static User currentUser;

    public static void main(String[] args) {
        seedProducts();

        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        currentUser = new User(name);
        System.out.println("Welcome, " + name + " to Instamart!");

        while (true) {
            System.out.println("\n1. View Products\n2. Add to Cart\n3. View Cart\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> displayProducts();
                case 2 -> addToCart();
                case 3 -> currentUser.cart.viewCart();
                case 4 -> {
                    System.out.println("Thanks for visiting Instamart!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void seedProducts() {
        products.add(new Product(1, "Milk", "Dairy", 50.0, 10));
        products.add(new Product(2, "Bread", "Bakery", 30.0, 20));
        products.add(new Product(3, "Apple", "Fruits", 20.0, 15));
        products.add(new Product(4, "Eggs", "Poultry", 70.0, 12));
    }

    static void displayProducts() {
        System.out.println("\nAvailable Products:");
        for (Product p : products) {
            System.out.println(p);
        }
    }

    static void addToCart() {
        displayProducts();
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();

        for (Product p : products) {
            if (p.id == id) {
                currentUser.cart.addProduct(p, quantity);
                return;
            }
        }
        System.out.println("Product not found.");
    }
}