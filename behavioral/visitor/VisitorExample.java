package behavioral.visitor;

import java.util.List;
import java.util.ArrayList;

// Use composite for the simple product
interface Product {
    void accept(ProductVisitor visitor);
}

// Leaf
class SimpleProduct implements Product {
    private double price;

    public SimpleProduct(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visitSimpleProduct(this);
    }
}

class ProductGroup implements Product {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public void accept(ProductVisitor visitor) {
        visitor.visitProductGroup(this);
        for (Product product : products) {
            product.accept(visitor);
        }
    }
}

// VISITOR

interface ProductVisitor {
    void visitSimpleProduct(SimpleProduct product);
    void visitProductGroup(ProductGroup productGroup);
}

class CostCalculatorVisitor implements ProductVisitor {
    private double totalCost = 0;

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public void visitSimpleProduct(SimpleProduct product) {
        totalCost += product.getPrice();
    }

    @Override
    public void visitProductGroup(ProductGroup productGroup) {
        //Aditionals especific operations for groups, if wanted
    }
}

public class VisitorExample {
    public static void main(String[] args) {
        SimpleProduct product1 = new SimpleProduct(20);
        SimpleProduct product2 = new SimpleProduct(30);

        SimpleProduct product3 = new SimpleProduct(5);

        ProductGroup group = new ProductGroup();
        group.addProduct(product1);
        group.addProduct(product2);

        ProductGroup group2 = new ProductGroup();
        group2.addProduct(product3);

        group.addProduct(group2);

        CostCalculatorVisitor costVisitor = new CostCalculatorVisitor();
        group.accept(costVisitor);

        System.out.println("Total Cost: " + costVisitor.getTotalCost());
    }
}
