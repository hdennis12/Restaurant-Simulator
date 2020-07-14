package main;

import presentation.*;
import business.*;
import data.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Aplicatia asteapta ca argument numele fisierului pentru serializare");
            exit(1);
        } else {
            //creeaza un restaurant
            Restaurant Grand = new Restaurant();

            //parcurge pasii corespunzatori pentru serializare
            RestaurantSerializator ser = new RestaurantSerializator(Grand, "");
            ser.dataLoad();
            Grand = ser.getRestaurant();

            //se face legatura cu interfata grafica
            AdministratorGUI boss = new AdministratorGUI();
            ChefGUI chef = new ChefGUI(Grand);
            WaiterGUI waiter = new WaiterGUI();

            //se creeaza un nou observator
            Grand.createNewObserver(chef, "bucatar");

            //se adauga functiile necesare pentru control
            AdministratorController administratorController = new AdministratorController(boss, Grand);
            WaiterController waiterController = new WaiterController(waiter, Grand);

            //se actualizeaza datele
            administratorController.updateTable();
            waiterController.updateTable();
            chef.updateTable();
        }
    }
}