package com.incubyte.sweetshop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class SweetShopUI extends JFrame {
    private SweetShopService service = new SweetShopService();
    private JTable table;
    private DefaultTableModel model;

    public SweetShopUI() {
        setTitle("Sweet Shop Management System");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Sweet Shop", JLabel.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(new Color(75, 0, 130));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        Font btnFont = new Font("Segoe UI", Font.BOLD, 15);

        buttonPanel.add(createButton("Add Sweet", btnFont, e -> addSweet()));
        buttonPanel.add(createButton("Delete Sweet", btnFont, e -> deleteSweet()));
        buttonPanel.add(createButton("View All", btnFont, e -> updateTable(service.getAllSweets())));
        buttonPanel.add(createButton("Search", btnFont, e -> search()));
        buttonPanel.add(createButton("Sort by Price", btnFont, e -> {
            List<Sweet> list = service.getAllSweets();
            list.sort(Comparator.comparingDouble(Sweet::getPrice));
            updateTable(list);
        }));
        buttonPanel.add(createButton("Sort by Quantity", btnFont, e -> {
            List<Sweet> list = service.getAllSweets();
            list.sort(Comparator.comparingInt(Sweet::getQuantity));
            updateTable(list);
        }));
        buttonPanel.add(createButton("Purchase Sweet", btnFont, e -> purchase()));
        buttonPanel.add(createButton("Restock Sweet", btnFont, e -> restore()));

        add(buttonPanel, BorderLayout.WEST);

        // JTable to display sweets
        model = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Quantity", "Category"}, 0);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(24);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private JButton createButton(String text, Font font, ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(new Color(204, 229, 255));
        btn.setFocusPainted(false);
        btn.addActionListener(listener);
        return btn;
    }

    private void updateTable(List<Sweet> sweets) {
        model.setRowCount(0); // clear table
        for (Sweet s : sweets) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getPrice(), s.getQuantity(), s.getCategory()});
        }
    }

    private void addSweet() {
        JTextField id = new JTextField(), name = new JTextField(), price = new JTextField(),
                   qty = new JTextField(), cat = new JTextField();
        JPanel p = new JPanel(new GridLayout(5, 2));
        p.add(new JLabel("ID:")); p.add(id);
        p.add(new JLabel("Name:")); p.add(name);
        p.add(new JLabel("Price:")); p.add(price);
        p.add(new JLabel("Quantity:")); p.add(qty);
        p.add(new JLabel("Category:")); p.add(cat);

        int result = JOptionPane.showConfirmDialog(this, p, "Add Sweet", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Sweet sweet = new Sweet(id.getText(), name.getText(),  cat.getText(),Double.parseDouble(price.getText()),
                    Integer.parseInt(qty.getText()));
            if (service.addSweet(sweet))
                JOptionPane.showMessageDialog(this, "Sweet added.");
            else
                JOptionPane.showMessageDialog(this, "Sweet with this ID already exists.");
            updateTable(service.getAllSweets());
        }
    }

    private void deleteSweet() {
        String id = JOptionPane.showInputDialog(this, "Enter Sweet ID to delete:");
        if (id != null) {
            boolean success = service.deleteSweet(id);
            JOptionPane.showMessageDialog(this, success ? "Sweet deleted." : "Not found.");
            updateTable(service.getAllSweets());
        }
    }

    private void search() {
        String[] choices = {"Name", "Category", "Price Range"};
        int c = JOptionPane.showOptionDialog(this, "Search by:", "Search", 0, 3, null, choices, choices[0]);

        List<Sweet> result = new ArrayList<>();
        if (c == 0) {
            String name = JOptionPane.showInputDialog(this, "Enter name:");
            result = service.searchByName(name);
        } else if (c == 1) {
            String cat = JOptionPane.showInputDialog(this, "Enter category:");
            result = service.searchByCategory(cat);
        } else if (c == 2) {
            double min = Double.parseDouble(JOptionPane.showInputDialog(this, "Min price:"));
            double max = Double.parseDouble(JOptionPane.showInputDialog(this, "Max price:"));
            result = service.searchByPriceRange(min, max);
        }
        updateTable(result);
    }

    private void purchase() {
        String id = JOptionPane.showInputDialog(this, "Sweet ID to purchase:");
        String qty = JOptionPane.showInputDialog(this, "Quantity to purchase:");
        boolean success = service.purchaseSweet(id, Integer.parseInt(qty));
        JOptionPane.showMessageDialog(this, success ? "Purchased!" : "Failed: Not enough stock or invalid ID.");
        updateTable(service.getAllSweets());
    }

    private void restore() {
        String id = JOptionPane.showInputDialog(this, "Sweet ID to restock:");
        String qty = JOptionPane.showInputDialog(this, "Quantity to add:");
        boolean success = service.restoreSweet(id, Integer.parseInt(qty));
        JOptionPane.showMessageDialog(this, success ? "Stock updated." : "Invalid sweet ID.");
        updateTable(service.getAllSweets());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SweetShopUI().setVisible(true));
    }
}
