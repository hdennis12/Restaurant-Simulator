package business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    public int orderID;
    private int table;
    public static int order_nr = 1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private String date = "";

    public Order(int orderID, int table) {
        if(orderID == -1)
            this.orderID = order_nr++;
        else
            this.orderID = orderID;
        Date dateAux = new Date();
        date = dateFormat.format(dateAux);
        this.table = table;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderDate() {
        return date;
    }

    public int getTable() {
        return table;
    }

    public int getHashCode() {
        return orderID % 5;
    }

}