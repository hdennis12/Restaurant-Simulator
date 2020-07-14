package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaiterGUI extends JFrame {
    private JButton orderButton = new JButton("Plaseaza comanda");
    private JButton printBillButton = new JButton("Cere nota(bill.txt");
    private JButton getDetailsButton = new JButton("Detalii comanda");
    private JTextField tableTextField = new JTextField(30);
    private JTextField prodTextField = new JTextField(30);

    private String[] columnsName = new String[]{"ID comanda", "Masa", "Data"};
    private DefaultTableModel model = new DefaultTableModel(columnsName, 0);
    private JTable table = new JTable(model);

    public WaiterGUI() {

        table.getColumn(columnsName[0]).setPreferredWidth(50);
        table.getColumn(columnsName[1]).setPreferredWidth(150);
        table.getColumn(columnsName[2]).setPreferredWidth(150);
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(600, 150));
        getContentPane().add(tablePanel, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.add(new JLabel("Masa: "));
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(tableTextField);
        panel1.add(Box.createVerticalStrut(30));
        panel1.add(new JLabel("Comanda(produse): "));
        panel1.add(Box.createVerticalStrut(5));
        panel1.add(prodTextField);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        panel2.add(orderButton);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(printBillButton);
        panel2.add(Box.createVerticalStrut(10));
        panel2.add(getDetailsButton);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        panel3.add(panel1);
        panel3.add(panel2);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));

        JPanel panel = new JPanel();
        panel.add(panel3);
        panel.add(tablePanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setPreferredSize(new Dimension(800, 200));

        this.setContentPane(panel);
        this.setTitle("Chelner");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }

    public String getTableUserInput() {
        return tableTextField.getText();
    }

    public void addCreateListener(ActionListener l) {
        orderButton.addActionListener(l);
    }

    public void addGenerateBillListener(ActionListener l) {
        printBillButton.addActionListener(l);
    }

    public String getUserAddInfo() {
        return prodTextField.getText();
    }

    public void addGetInfoListener(ActionListener l) {
        getDetailsButton.addActionListener(l);
    }

    public String getProductsUserInput() {
        return prodTextField.getText();
    }

    public String getProductsAddInfo() {
        return prodTextField.getText();
    }

    public void showErrMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", 0);
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}