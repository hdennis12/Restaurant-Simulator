package data;

import java.io.*;
import business.Restaurant;

public class RestaurantSerializator {
    private File file;
    private Restaurant rest;

    public RestaurantSerializator(Restaurant rest, String fileName) {
        this.rest = rest;
        file = new File(fileName);
    }

    public Restaurant getRestaurant() {
        return rest;
    }

    public void dataLoad() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            rest = (Restaurant) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Missing file");
            e.printStackTrace();
        }

    }
    public void dataSave() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(rest);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Missing file");
        }
    }
    

}