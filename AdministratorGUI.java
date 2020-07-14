package presentation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;

public class AdministratorGUI extends JFrame {

    private String[] firstRowTitles = new String[]{"Denumire", "Pret", "tip"};
    private DefaultTableModel model = new DefaultTableModel(firstRowTitles, 0);
    private JTable table = new JTable(model);
    private JButton addBaseProductButton = new JButton("Adauga produs simplu");
    private JButton modifyButton = new JButton("Modifica pret");
    private JButton deleteButton = new JButton("Sterge");
    private JTextField baseProductTextField = new JTextField(30);
    private JTextField priceTextField = new JTextField(10);
    private JButton addCompositeProductButton = new JButton("Adauga compozit");
    private JButton productDetailsButton = new JButton("Detalii produs");
    private JTextField compositeProductTextField = new JTextField(30);
    private JTextField componentsTextField = new JTextField(50);


    public AdministratorGUI() {

        table.getColumn(firstRowTitles[0]).setPreferredWidth(150);
        table.getColumn(firstRowTitles[1]).setPreferredWidth(50);
        table.getColumn(firstRowTitles[2]).setPreferredWidth(150);
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(400, 500));
        getContentPane().add(tablePanel, BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panelLeft = new JPanel();

        JRadioButton j1 = new JRadioButton("Base product");
        JRadioButton j2 = new JRadioButton("Composite product");

        panel1.add(j1);
        panel1.add(j2);
        panel1.setLayout(new FlowLayout());

        panel4.add(new JLabel("Nume produs simplu: "));
        panel4.add(Box.createVerticalStrut(5));
        panel4.add(baseProductTextField);

        panel4.add(Box.createVerticalStrut(15));

        panel4.add(new JLabel("Pret: "));
        panel4.add(Box.createVerticalStrut(5));
        panel4.add(priceTextField);
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

        panel5.add(Box.createVerticalStrut(5));
        panel5.add(addBaseProductButton);
        panel5.add(Box.createVerticalStrut(5));
        panel5.add(modifyButton);
        panel5.add(Box.createVerticalStrut(5));
        panel5.add(deleteButton);
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        panel5.add(Box.createVerticalStrut(15));

        panel2.add(panel4);
        panel2.add(panel5);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));

        panel6.add(Box.createVerticalStrut(15));
        panel6.add(new JLabel("Nume compozit: "));
        panel6.add(Box.createVerticalStrut(5));
        panel6.add(compositeProductTextField);
        panel6.add(Box.createVerticalStrut(15));
        panel6.add(new JLabel("Componente(produse): "));
        panel6.add(Box.createVerticalStrut(5));
        panel6.add(componentsTextField);
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));

        panel7.add(addCompositeProductButton);
        panel7.add(Box.createVerticalStrut(5));
        panel7.add(productDetailsButton);
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

        panel3.add(panel6);
        panel3.add(panel7);
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));


        panelLeft.setPreferredSize(new Dimension(600, 200));
        panelLeft.add(panel1);
        panelLeft.add(panel2);
        panelLeft.add(panel3);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1000, 300));
        panel.add(panelLeft);
        panel.add(tablePanel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Administrator");
        this.pack();
        this.setVisible(true);

    }

    public void createAddProductListener(ActionListener l) {
        addBaseProductButton.addActionListener(l);
    }

    public void createDetailListener(ActionListener l) {
        productDetailsButton.addActionListener(l);
    }

    public void createModifyListener(ActionListener l) {
        modifyButton.addActionListener(l);
    }

    public void createDeleteListener(ActionListener l) {
        deleteButton.addActionListener(l);
    }

    public void createAddCompositeProductListener(ActionListener l) {
        addCompositeProductButton.addActionListener(l);
    }

    public void createSelectionListener(MouseListener l) {
        table.addMouseListener(l);
    }

    public String getUserProductNameInput() {
        return baseProductTextField.getText();
    }

    public String getComparedString() {
        return baseProductTextField.getText();
    }

    public String getUserPriceInput() {
        return priceTextField.getText();
    }

    public String getComparedStringV2() {
        return baseProductTextField.getText();
    }

    public JTextField getNameTextField() {
        return baseProductTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.baseProductTextField = nameTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setPriceTextField(JTextField priceTextField) {
        this.priceTextField = priceTextField;
    }

    public String getUserCompositeNameInput() {
        return compositeProductTextField.getText();
    }

    public String getUserComponentsInput() {
        return componentsTextField.getText();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTextField getCompositeNameTextField() {
        return compositeProductTextField;
    }

    public void setCompositeNameTextField(JTextField compositeNameTextField) {
        this.compositeProductTextField = compositeNameTextField;
    }

    public JTextField getComponentsTextField() {
        return componentsTextField;
    }

    public void setComponentsTextField(JTextField componentsTextField) {
        this.componentsTextField = componentsTextField;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public void showErrMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", 0);
    }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}