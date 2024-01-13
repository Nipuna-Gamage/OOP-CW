import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//The ShopGUI class represents a graphical user interface for a shopping application.
// Users can browse products, view details, add items to the shopping cart, and view the shopping cart.
public class ShopGUI extends JFrame {
    private List<Product> producList; // List of available products in the shop.
    private List<Product> shopCartList= new ArrayList<>(); // List to store products added to the shopping cart.
    private JComboBox<String> producTypeComBox; // ComboBox for selecting product categories.
    private JTable producTable;
    private JTextArea producTextArea;
    private JButton addToCartButton;
    private JButton viewShopCartButton;
    private JScrollPane tableScrollPane;
    public ShopGUI(List<Product> producList) {
        this.producList = producList;
        initializeGUI();
        populateTable("All");
        // Add a ListSelectionListener to update the details when a product is selected.
        producTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = producTable.getSelectedRow();
                if (selectedRow >= 0) {
                    displayProducDetails(producList.get(selectedRow));
                }
            }
        });
    }
    //Displays detailed information about the selected product in the TextArea.
    private void displayProducDetails(Product product) {
        String ECDetails = getECDetails(product);
        String details = "Selected Product - Details \n\nProduct ID: " + product.getProductID() + "\n"
                + "Category: " + product.getClass().getSimpleName()
                + "\n" + "Name: " + product.getProductName() + "\n"
                + ECDetails + "Available Items: " + "\n"
                + product.getAvailableItems();
        producTextArea.setText(details);
    }
    //Retrieves additional details for Electronics and Clothing products.
    private String getECDetails(Product product) {
        if (product instanceof Electronics electronicProduct) {
            return "Brand: "+ electronicProduct.getBrand() + "\nWarranty Period: " + electronicProduct.getWarrantyPeriod() +" Weeks";
        } else if (product instanceof Clothing clothingProduct) {
            return "Size: " +clothingProduct.getSize() + "\nColour: " + clothingProduct.getColor();
        } else {
            return "";
        }
    }
    //Initializes the graphical user interface components.
    private void initializeGUI() {
        setTitle("Westminster Shopping Centre ");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ComboBox for selecting product categories.
        String[] productTypes = {"All", "Electronics", "Clothes"};
        producTypeComBox = new JComboBox<>(productTypes);
        producTypeComBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) producTypeComBox.getSelectedItem();
                populateTable(selectedType);
            }
        });
        producTable = new JTable(); //Table for displaying product information.
        tableScrollPane = new JScrollPane(producTable);
        producTextArea = new JTextArea(); //TextArea for displaying detailed information about selected products.
        producTextArea.setEditable(false);
        addToCartButton = new JButton("Add to Shopping Cart"); //Buttons for adding products to the shopping cart and viewing the shopping cart.
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = producTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Product selectRow = producList.get(selectedRow);
                    shopCartList.add(selectRow);
                    JOptionPane.showMessageDialog(ShopGUI.this, "Product added to cart");
                } else {
                    JOptionPane.showMessageDialog(ShopGUI.this, "Please select a product add to cart", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        viewShopCartButton = new JButton("Shopping Cart");
        viewShopCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCartGUI();
            }
        });
        //Panel for organizing controls.
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Product Category"));
        controlPanel.add(producTypeComBox);
        controlPanel.add(viewShopCartButton);
        JPanel productDetailsPanel = new JPanel(); //Panel for displaying product details and adding to cart.
        productDetailsPanel.setLayout(new BoxLayout(productDetailsPanel, BoxLayout.Y_AXIS));
        productDetailsPanel.add(producTextArea);
        productDetailsPanel.add(addToCartButton);
        JPanel mainPanel = new JPanel(new BorderLayout()); //Main panel for layout organization.
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(productDetailsPanel, BorderLayout.SOUTH);
        add(controlPanel, BorderLayout.NORTH); //Adding panels to the frame.
        add(mainPanel, BorderLayout.CENTER);
    }
    //Populates the product table based on the selected product category.
    private void populateTable(String productType) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Price");
        model.addColumn("Info");
        for (Product product : producList) {
            if (productType.equals("All") || (productType.equals("Electronics") && product instanceof Electronics) || (productType.equals("Clothes") && product instanceof Clothing)) {
                String MoreInfo = getMoreInfo(product);
                model.addRow(new Object[]{
                        product.getProductID(),
                        product.getProductName(),
                        product.getClass().getSimpleName(),
                        product.getPrice(),
                        MoreInfo
                });
            }
        }
        producTable.setModel(model);
    }
    //Retrieves additional details for Electronics and Clothing products for the product table.
    private String getMoreInfo(Product product) {
        if (product instanceof Electronics electronicProduct) {
            return electronicProduct.getBrand() + ", " + electronicProduct.getWarrantyPeriod() + ", Weeks Warranty";
        } else if (product instanceof Clothing clothingProduct) {
            return clothingProduct.getSize() + ", " + clothingProduct.getColor();
        } else {
            return "";
        }
    }
    //Opens the shopping cart GUI in the event dispatch thread.
    private void openCartGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CartGUI(shopCartList).setVisible(true);
            }
        });
    }
    //Main method to create an instance of ShopGUI and display the shopping application
    public static void main(String[] args) {
        WestminsterShoppingManager manager;
        manager = new WestminsterShoppingManager();
        manager.loadProducts();
        SwingUtilities.invokeLater(() -> {
            ShopGUI ShopGUI = new ShopGUI(manager.getproducList());
            ShopGUI.setVisible(true);
        });
    }
}