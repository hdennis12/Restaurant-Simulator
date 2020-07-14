package business;

import java.util.List;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private List<MenuItem> products;

    public CompositeProduct(String name, ArrayList<MenuItem> products) {
        super(name, 0);
        this.products = products;
        this.setPrice(calculatePrice());
    }

    public List<MenuItem> getProducts() {
        return products;
    }

    public void addProduct(MenuItem product) {
        products.add(product);
        this.setPrice(this.calculatePrice());
    }

    public double calculatePrice() {
        double sum = 0;
        for (MenuItem item : products) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void removeProduct(MenuItem product) {
        setPrice(this.getPrice()-product.calculatePrice());
        products.remove(product);
    }

    public boolean find(MenuItem item) {
        for (MenuItem product : products) {
            if (product.find(item)) {
                return true;
            }
        }
        return false;
    }
}