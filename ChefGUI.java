package presentation;

import business.Restaurant;
import business.Order;


import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ChefGUI extends JFrame implements Observer {

    private String[] firstLineTitles = new String[]{"Order ID", "produse"};
    private DefaultTableModel model = new DefaultTableModel(firstLineTitles, 0);
    private JTable table = new JTable(model);
    private Restaurant resObs;

    public ChefGUI(Restaurant restaurantObservable) {

        JPanel panel = new JPanel();

        table.getColumn(firstLineTitles[0]).setPreferredWidth(150);
        table.getColumn(firstLineTitles[1]).setPreferredWidth(550);
        this.resObs = restaurantObservable;
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(450, 120));
        getContentPane().add(tablePanel, BorderLayout.CENTER);

        panel.add(tablePanel);

        this.setContentPane(panel);
        this.setTitle("Bucatar");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public Order monitor() {
        deleteRows();
        updateTable();
        this.showMessage("Comanda noua!");
        return null;
    }

    public void updateTable() {
        Object[] row = new Object[2];
        for (Order key : resObs.getOrders().keySet()) {
            row[0] = key.getOrderID();
            row[1] = resObs.getOrderInfo(key);
            model.addRow(row);
        }
    }

    public void deleteRows() {
        for (int poz = model.getRowCount() - 1; poz >= 0; poz--) {
            model.removeRow(poz);
        }
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }



}