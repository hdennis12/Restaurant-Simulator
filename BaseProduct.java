package business;

public class BaseProduct extends MenuItem {

    public BaseProduct(String name, double price) {
        super(name, price);
    }

    public double calculatePrice() {
        return this.getPrice();
    }

    public boolean find(MenuItem item) {
        if (this.equals(item)) {
            return true;
        }
        return false;
    }
}