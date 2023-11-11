package creationals.factory_method;

interface Creator {
    Product createProduct();
}

interface Product {
}

class WoodMaterial implements Product {
}

class WoodCreator implements Creator {
    @Override
    public Product createProduct() {
        return new WoodMaterial();
    }
}

class MetalMaterial implements Product {
    private double weight;

    public MetalMaterial(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

class MetalCreator implements Creator {
    private double weight;
    public MetalCreator(double weight) {
        this.weight = weight;
    }
    @Override
    public Product createProduct() {
        return new MetalMaterial(this.weight);
    }
}

class CartonMaterial implements Product {
    private double thickness;
    public CartonMaterial() {
    }
    public double getThickness() {
        return thickness;
    }
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}

class CartonCreator implements Creator {
    @Override
    public Product createProduct() {
        return new CartonMaterial();
    }
}
class FactoryMethod {
    public static void main(String[] args){
        Creator creators[] = new Creator[3];
        creators[0] = new WoodCreator();
        creators[1] = new MetalCreator(10.0);
        creators[2] = new CartonCreator();

        for (Creator creator : creators) {
            Product product = creator.createProduct();
            if(product instanceof CartonMaterial){
                ((CartonMaterial) product).setThickness(0.1);
            }
            System.out.println(product.getClass().getSimpleName());
        }
    }
}
