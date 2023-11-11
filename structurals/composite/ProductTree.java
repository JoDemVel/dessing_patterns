package structurals.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Product {
    String getName();
    double getPrice();
    String getDescription(int depth);
}

class IndividualProduct implements Product {
    private String name;
    private double price;
    private String description;

    public IndividualProduct(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription(int depth) {
        String prefix = "-".repeat(depth);
        return prefix + " " + name + ": " + description;
    }
}

class ProductCategory implements Product {
    private String name;
    private List<Product> products = new ArrayList<>();

    public ProductCategory(String name) {
        this.name = name;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    @Override
    public String getDescription(int depth) {
        String prefix = "-".repeat(depth);
        StringBuilder description = new StringBuilder(prefix + " Category: " + name + "\n");
        description.append(products.stream()
                .map(product -> product.getDescription(depth + 1))
                .collect(Collectors.joining("\n")));
        return description.toString();
    }
}

public class ProductTree {
    public static void main(String[] args) {

        Product product1 = new IndividualProduct("Laptop", 1000.0, "High-end laptop");
        Product product2 = new IndividualProduct("Mouse", 20.0, "Wireless mouse");
        Product product3 = new IndividualProduct("Keyboard", 50.0, "Mechanical keyboard");

        ProductCategory category1 = new ProductCategory("Computers");
        category1.addProduct(product1);
        category1.addProduct(product2);

        ProductCategory category2 = new ProductCategory("Peripherals");
        category2.addProduct(product3);
        category2.addProduct(category1);

        System.out.println("Information about Category 2:");
        System.out.println("Name: " + category2.getName());
        System.out.println("Total Price: $" + category2.getPrice());
        System.out.println("Description: \n" + category2.getDescription(0));
    }
}