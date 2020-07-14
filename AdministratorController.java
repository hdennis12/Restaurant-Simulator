package data;

import business.BaseProduct;
import business.CompositeProduct;
import business.Restaurant;
import business.MenuItem;
import presentation.AdministratorGUI;

import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministratorController {
    private Restaurant model;
    private AdministratorGUI view;

    public AdministratorController(AdministratorGUI view, Restaurant model) {
        this.model = model;
        this.view = view;
        addAllListeners(model, view);
    }

    public void updateTable() {
        String[] firstLine = new String[3];
        HashMap<String, MenuItem> array;
        array = model.getMenu();
        System.out.println(array);
        for (String ks : model.getMenu().keySet()) {
            if (ks != null) {
                MenuItem i = model.getMenu().get(ks);
                firstLine[0] = i.getName();
                firstLine[1] = Double.toString(i.getPrice());
                if (i instanceof BaseProduct)
                    firstLine[2] = "simplu";
                else
                    firstLine[2] = "compozit";
                view.getModel().addRow(firstLine);
            }
        }
    }

    private void addAllListeners(Restaurant model, AdministratorGUI view) {
        view.createAddProductListener(new BaseProductListener());
        view.createModifyListener(new ModifyListener());
        view.createDeleteListener(new DeleteListener());
        view.createDetailListener(new DetListener());
        view.createSelectionListener(new SelectionListener());
        view.createAddCompositeProductListener(new CompositeProductListener());
    }

    class BaseProductListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String[] itemRow = new String[3];
            if (!view.getUserProductNameInput().isEmpty() && !view.getUserPriceInput().isEmpty()) {
                if (!model.searchItemInMenu(view.getUserProductNameInput())) {
                    double price = Double.parseDouble(view.getUserPriceInput());
                    String itemName = view.getUserProductNameInput();
                    model.createProduct(itemName, price);
                    itemRow[0] = itemName;
                    itemRow[1] = view.getUserPriceInput();
                    itemRow[2] = "Produs simplu";

                    view.getModel().addRow(itemRow);

                } else {
                    view.showErrMessage("Produsul a fost deja adaugat in meniu");
                }
            } else {
                view.showErrMessage("Datele produsului nu sunt valide");
            }
        }
    }

    class ModifyListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (!view.getUserProductNameInput().isEmpty() && !view.getUserPriceInput().isEmpty()) {
                if (selectedRow >= 0 && Double.parseDouble(view.getUserPriceInput()) >= 0) {
                    String name = view.getUserProductNameInput();
                    double newPrice = Double.parseDouble(view.getUserPriceInput());
                    model.modifyProduct(name, newPrice);
                    deleteRows();
                    updateTable();
                } else {
                    view.showErrMessage("Pretul nu a putut fi modificat");
                }
            } else {
                view.showErrMessage("Pretul nu a putut fi modificat");
            }
        }

        public void deleteRows() {
            for (int i = view.getModel().getRowCount() - 1; i >= 0; i--) {
                view.getModel().removeRow(i);
            }
        }
    }

    class DeleteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                String name = view.getUserProductNameInput();
                model.deleteProduct(name);
                deleteRows();
                updateTable();
            } else {
                view.showErrMessage("Eroare la stergere!");
            }
        }

        public void deleteRows() {
            for (int i = view.getModel().getRowCount() - 1; i >= 0; i--) {
                view.getModel().removeRow(i);
            }
        }
    }

    class CompositeProductListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String[] itemRow = new String[3];
            if (!view.getUserCompositeNameInput().isEmpty() && !view.getUserComponentsInput().isEmpty()) {
                if (!model.searchItemInMenu(view.getUserCompositeNameInput())) {
                    String itemName = view.getUserCompositeNameInput();
                    ArrayList<String> products = compositeSplit(view.getUserComponentsInput());
                    try {
                        CompositeProduct product = model.createProduct(itemName, products);
                        itemRow[0] = itemName;
                        itemRow[1] = Double.toString(product.getPrice());
                        itemRow[2] = "Produs compozit";

                        view.getModel().addRow(itemRow);
                    } catch (NullPointerException exception) {
                        view.showErrMessage("Datele produsului nu sunt valide");
                    }
                } else {
                    view.showErrMessage("Produsul exista deja in meniu");
                }
            } else {
                view.showErrMessage("Datele produsului nu sunt valide");
            }
        }

        public ArrayList<String> compositeSplit(String string) {
            String[] nameArray = string.split(", ");
            ArrayList<String> resultArray = new ArrayList<>();
            for (String s : nameArray) {
                resultArray.add(s);
            }
            return resultArray;
        }

    }

    class SelectionListener implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            view.getNameTextField().setText(view.getModel().getValueAt(selectedRow, 0).toString());
            view.getPriceTextField().setText(view.getModel().getValueAt(selectedRow, 1).toString());
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

    }

    class DetListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {
                int selectedRow = view.getTable().getSelectedRow();
                String productName = view.getModel().getValueAt(selectedRow, 0).toString();
                view.showMessage(model.getProductInfo(productName));

            } catch (ArrayIndexOutOfBoundsException | NullPointerException ee) {
                view.showErrMessage("Nu se pot oferi detalii despre produs");
            }
        }
    }


}