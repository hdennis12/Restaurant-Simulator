package business;

import java.util.ArrayList;

public interface IRestaurantProcessing {

    /**
     * Creeaza un produs de tip baseProduct sau compositeProduct
     * @param name numele produsului
     * @param price pretul produsului
     * @throws IllegalArgumentException pentru cazurile in care functia primeste parametrii invalizi(string null sau pret < 0)
     * @throws AssertionError produsul nu poate fi creat
     * @return un produs de baza
     * @pre !name.isEmpty() && price >= 0
     * @post !baseProduct.getName().isEmpty() && baseProduct.getPrice() >= 0
     * @invariant isInitialized()
     */
    BaseProduct createProduct(String name, double price);

    /**
     * Metoda creeaza un compozit de produse.
     * @param name numele produsului
     * @param nameProducts numele produselor ce intra in compozit
     * @throws IllegalArgumentException daca stringul nume este null, gol sau ArrayList-ul cu produse este gol
     * @throws AssertionError nu s-a putut creea un compozit de produse
     * @return un compozit de produse in cazul in care s-a reusit crearea acestuia
     * @pre !name.isEmpty() && !nameProducts.isEmpty()
     * @post !compositeProduct.getProducts().isEmpty()
     * @invariant isInitialized()
     */
    CompositeProduct createProduct(String name, ArrayList<String> nameProducts);

    /**
     * Metoda sterge un MenuItem din restaurantItems ce are numele dat ca parametru.
     * @param name numele produsului care se doreste a fi sters
     * @throws IllegalArgumentException daca numele este nul sau fol
     * @throws AssertionError in cazul in care this.order sau this.restaurantItems devin nule
     * @pre !name.isEmpty()
     * @invariant isInitialized()
     */
    void deleteProduct(String name);

    /**
     * Modifica pretul unui produs ales
     * @param name numele produsului
     * @param newPrice pretul actualizat
     * @throws IllegalArgumentException nume null sau pret negativ
     * @throws AssertionError in cazul in care campurile orders sau restaurantItems devin nule
     * @pre !name.isEmpty() && newPrice >= 0
     * @invariant isInitialized()
     */
    void modifyProduct(String name,  double newPrice);

    /**
     * Plaseaza o comanda noua
     * @param table numarul mesei
     * @param orderedItems produsele comandate
     * @return comanda
     * @throws NullPointerException daca nu s-au gasit produsele date ca argument in ArrayListul orderItems
     * @throws IllegalArgumentException daca numarul mesei este negativ si lista de produse comandate este goala
     * @throws AssertionError in cazul in care comanda nu s-a putut crea
     * @pre !orderedItems.isEmpty() && table >= 0
     * @post orders.get(order) != null
     * @invariant isInitialized()
     */
    Order placeOrder(int table, ArrayList<String> orderedItems);

    /**
     * Calculeaza pretul total pentru o comanda
     * @param order comanda al carui pret se doreste a se calcula
     * @return pretul comenzii
     * @throws IllegalArgumentException comanda data ca parametru este nula
     * @throws AssertionError pretul total este negativ sau this.orders sau this.restaurantItems devin nule
     * @pre order != null
     * @post price >= 0
     * @invariant isInitialized()
     */
    double calculatePrice(Order order);

    /**
     * Construieste un string care descrie nota de plata asociata comenzii
     * @param order comanda asupra careia se genereaza chitanta
     * @return textul care va fi scris pe nota de plata
     * @throws AssertionError daca this.orders sau this.restaurantItems devin nule
     * @throws IllegalArgumentException daca comanda primita ca paramteru e nula.
     * @pre order != null
     * @invariant isInitialized()
     */
    String generateBill(Order order);
}