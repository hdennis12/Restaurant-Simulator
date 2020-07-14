package business;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {

    private static final long serialVersionUID = 15952774314598L;
    private LinkedHashMap<String, MenuItem> menu = new LinkedHashMap<>();
    private HashMap<Order, List<MenuItem>> orders = new HashMap<>();

    /**
     * Creeaza un produs de tip BaseProduct
     * @param name  numele produsului
     * @param price pretul produsului
     * @return un produs de baza in cazul in care s-a reusit crearea acestuia
     * @throws IllegalArgumentException daca stringul nume este null, gol sau pretul e negativ
     * @throws AssertionError           nu s-a putut creea un produs de baza
     */
    public BaseProduct createProduct(String name, double price) {
        if (name != null && price >= 0) {
            BaseProduct baseProduct = new BaseProduct(name, price);
            menu.put(name.toLowerCase(), baseProduct);
            assert isInitialized() : "Instanta nu este initializata corect";
            assert !baseProduct.getName().isEmpty() && baseProduct.getPrice() != 0 : "Nu se poate crea produsul";
            return baseProduct;
        } else {
            throw new IllegalArgumentException("Parametru null pentru nume sau pret negativ");
        }
    }

    /**
     * Creeaza un produs de tip CompositeProduct
     * @param name         numele produsului
     * @param nameProducts numele produselor ce intra in compozit
     * @return un compozit de produse in cazul in care s-a reusit crearea acestuia
     * @throws IllegalArgumentException daca stringul nume este null, gol sau ArrayList-ul cu produse este gol
     * @throws AssertionError           nu s-a putut creea un compozit de produse
     */

    public CompositeProduct createProduct(String name, ArrayList<String> nameProducts) throws NullPointerException {
        if (name != null && !nameProducts.isEmpty()) {
            ArrayList<MenuItem> products = new ArrayList<>();
            for (String productName : nameProducts) {
                products.add(menu.get(productName.toLowerCase()));
            }
            CompositeProduct compositeProduct = new CompositeProduct(name, products);
            menu.put(name.toLowerCase(), compositeProduct);
            assert !compositeProduct.getProducts().isEmpty() : "Acest produs nu poate fi creat";
            return compositeProduct;
        } else {
            throw new IllegalArgumentException("Parametri incorecti");
        }
    }

    /**
     * Primeste un string si pe baza acestuia cauta in corespondent in menu
     * @param name numele produsului cautat.
     * @return true in caz ca s-a gasit produsul cautat si false in caz contrar
     */
    public boolean searchItemInMenu(String name) {
        for (String key : menu.keySet()) {
            if (key.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pe baza numelui primit ca parametru, sterge un produs din menu
     * @param name numele produsului care se doreste a fi sters
     * @throws IllegalArgumentException daca numele este nul sau gol
     * @throws AssertionError           in cazul in care order sau menu devin nule
     */
    public void deleteProduct(String name) {
        if (!name.isEmpty()) {
            MenuItem deletedProduct = menu.get(name);
            menu.remove(name);
            menu.entrySet().removeIf(product -> (product.getValue().find(deletedProduct)));
        } else {
            throw new IllegalArgumentException("Parametru null pentru nume");
        }
    }

    /**
     * Modifica pretul unui produs aflat deja in meniu
     * @param name     numele produsului de modificat
     * @param newPrice pretul actualizat
     * @throws IllegalArgumentException string null sau pret negativ
     * @throws AssertionError   in cazul in care orders sau menu devin nule
     */
    public void modifyProduct(String name, double newPrice) {
        if (name != null) {
            MenuItem modifiedItem = menu.get(name.toLowerCase());
            modifiedItem.setPrice(newPrice);

            for (Map.Entry<String, MenuItem> product : menu.entrySet()) {
                if ((product.getValue().find(modifiedItem))) {
                    product.getValue().setPrice(product.getValue().calculatePrice());
                }
            }
        } else {
            throw new IllegalArgumentException("Parametru null pentru produs");
        }
    }

    /**
     * Plaseaza o noua comanda
     * @param table        numarul mesei
     * @param orderedItems produsele comandate
     * @return comanda creata.
     * @throws NullPointerException     daca nu s-au gasit produsele date ca argument in ArrayListul orderItems
     * @throws IllegalArgumentException daca numarul mesei este negativ si lista de produse comandate este goala
     * @throws AssertionError           in cazul in care comanda nu s-a putut crea
     */
    public Order placeOrder(int table, ArrayList<String> orderedItems) throws NullPointerException {
        if (table > 0 && !orderedItems.isEmpty()) {
            Order order = new Order(-1, table);
            List<MenuItem> items = new ArrayList<>();

            for (String item : orderedItems) {
                MenuItem it = menu.get(item.toLowerCase());
                if (it != null) {
                    items.add(it);
                } else {
                    return null;
                }
            }
            orders.put(order, items);
            this.announceObserver();
            assert orders.get(order) != null : "Comanda nu poate fi creata";
            return order;
        } else {
            throw new IllegalArgumentException("Masa este definita cu numar negativ sau lista nu contine elemente");
        }
    }

    /**
     * Calculeaza pretul total al comenzii
     * @param order comanda al carui pret se doreste a se calcula
     * @return pretul comenzii
     * @throws IllegalArgumentException parametru null pentru comanda
     * @throws AssertionError   pretul total este negativ sau this.orders sau this.menu devin nule
     */
    public double calculatePrice(Order order) {
        double price = 0;
        if (order != null) {
            List<MenuItem> items = orders.get(order);
            for (MenuItem item : items) {
                if (item != null)
                    price += item.calculatePrice();
            }
            return price;
        } else {
            throw new IllegalArgumentException("Nu exista comanda");
        }
    }

    /**
     * Construieste un string care reprezinta nota de plata
     * @param order comanda asupra careia se genereaza chitanta
     * @return un string ce contine continutul chitantei
     * @throws AssertionError           daca this.orders sau this.menu devin nule
     * @throws IllegalArgumentException daca comanda primita ca paramteru e nula.
     */
    public String generateBill(Order order) {
        if (order != null) {
            String str = "                                             Nota de plata\n";
            str += ("ID Comanda: " + order.getOrderID() + "\n");
            str+= "Produse:\n";
            List<MenuItem> orderedItems = orders.get(order);
            for (MenuItem item : orderedItems) {
                if (item != null)
                    str += (item.getName() + "  :  " + item.getPrice() + "\n");
            }
            str += ("Pret total: " + calculatePrice(order) + "\n");
            return str;
        } else {
            throw new IllegalArgumentException("Comanda invalida");
        }
    }

    /**
     * Primeste un nume de produs ca parametru si furnizeaza date despre acesta
     * @param name numele produsului
     * @return un string cu informatii despre produs
     */
    public String getProductInfo(String name) {
        if (name != null) {
            String info = "";
            MenuItem item = menu.get(name);
            if (item instanceof BaseProduct) {
                info += "Produs simplu. Detalii in meniu";
            } else {
                info += "Componentele produsului " + name + " sunt: ";
                for (MenuItem product : ((CompositeProduct) item).getProducts()) {
                    if (product != null) {
                        if (!product.equals(((CompositeProduct) item).getProducts().get(((CompositeProduct) item).getProducts().size() - 1))) {
                            info = info + product.getName() + ", ";
                        } else {
                            info += product.getName();
                        }
                    }
                }
            }
            return info;
        } else {
            throw new IllegalArgumentException("Parametru null pentru nume");
        }

    }

    /**
     * Primeste o comanda ca parametru si returneaza informatii despre aceasta
     * @param order comanda
     * @return un string cu informatii despre comanda.
     */
    public String getOrderInfo(Order order) {
        String info = "";
        List<MenuItem> orderedItems = orders.get(order);
        for (MenuItem item : orderedItems) {
            if (item != null) {
                if (!item.equals(orderedItems.get(orderedItems.size() - 1))) {
                    info = info + item.getName() + ", ";
                } else {
                    info += item.getName();
                }
            }
        }
        return info;
    }

    private boolean isInitialized() {
        if (this.orders != null && this.menu != null) {
            return true;
        }
        return false;
    }

    public HashMap<String, MenuItem> getMenu() {
        return menu;
    }

    public HashMap<Order, List<MenuItem>> getOrders() {
        return orders;
    }


}