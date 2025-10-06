import java.util.*;
import java.util.stream.*;
import java.util.Optional;

class Product {
    String name;
    double price;
    String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String toString() {
        return name + " | " + category + " | " + price;
    }
}

public class PartC_ProductStreamOps {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 80000, "Electronics"),
            new Product("Phone", 50000, "Electronics"),
            new Product("Shirt", 1500, "Clothing"),
            new Product("Jeans", 2000, "Clothing"),
            new Product("Watch", 7000, "Accessories")
        );

        // Group products by category
        Map<String, List<Product>> grouped = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));
        System.out.println("Grouped by category: " + grouped);

        // Most expensive product in each category
        Map<String, Optional<Product>> mostExpensive = products.stream()
            .collect(Collectors.groupingBy(
                p -> p.category,
                Collectors.maxBy(Comparator.comparingDouble(p -> p.price))
            ));
        System.out.println("Most expensive products: " + mostExpensive);

        // Average price of all products
        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(p -> p.price));
        System.out.println("Average price: " + avgPrice);
    }
}
