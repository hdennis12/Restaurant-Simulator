package data;

import presentation.WaiterGUI;
import business.Order;
import business.Restaurant;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WaiterController {
    private WaiterGUI view;
    private Restaurant model;
    public static int bill_nr = 0;


    public WaiterController(WaiterGUI view, Restaurant model) {
        this.view = view;
        this.model = model;
        view.addGetInfoListener(new GetInfoListener());
        view.addCreateListener(new CreateListener());
        view.addGenerateBillListener(new GenerateBillListener());

    }

    public void updateTable() {
        Object[] row = new Object[3];
        for (Order key : model.getOrders().keySet()) {
            row[0] = key.getOrderID();
            row[1] = key.getTable();
            row[2] = key.getOrderDate();
            this.view.getModel().addRow(row);
        }
    }

    class GetInfoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = view.getTable().getSelectedRow();
                int table = Integer.parseInt(view.getModel().getValueAt(selectedRow, 1).toString());
                int orderID = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());

                Order order = new Order(orderID, table);
                view.showMessage(model.getOrderInfo(order));
            } catch (ArrayIndexOutOfBoundsException ee) {
                view.showErrMessage("Comanda invalida");
            }
        }
    }

    class CreateListener implements ActionListener {
        Object[] row = new Object[3];

        public void actionPerformed(ActionEvent e) {
            try {
                ArrayList orderedItems = splitString(view.getProductsUserInput());
                int table = Integer.parseInt(view.getTableUserInput());
                Order order = model.placeOrder(table, orderedItems);
                row[0] = String.valueOf(order.getOrderID());
                row[1] = String.valueOf(order.getTable());
                row[2] = order.getOrderDate();
                view.getModel().addRow(row);
            } catch (NullPointerException | NumberFormatException ee) {
                view.showErrMessage("Comanda invalida");
            }
        }

        public ArrayList<String> splitString(String string) {
            String[] splited = string.split(", ");
            ArrayList<String> resultArray = new ArrayList<>();
            for (String str : splited) {
                resultArray.add(str);
            }
            return resultArray;
        }
    }

    class GenerateBillListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            FileWriter fileWriter = new FileWriter();
            try {
                int selectedRow = view.getTable().getSelectedRow();
                int table = Integer.parseInt(view.getModel().getValueAt(selectedRow, 1).toString());
                int orderID = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
                Order order = new Order(orderID, table);
                String string = model.generateBill(order);
                fileWriter.writeInFile(string, "bill" + (++bill_nr) + ".txt");
            } catch (FileNotFoundException | UnsupportedEncodingException | ArrayIndexOutOfBoundsException ee) {
                System.out.println("Eroare la generare chitanta");
                view.showErrMessage("Eroare la generare chitanta!\nNu ati selectat produsul!");
            }
        }
    }
}
