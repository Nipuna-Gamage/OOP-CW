import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class CartGUI extends JFrame {
    private List<Product> shopCartList; // List of products in the shopping cart.
    private JTable cartTable; // Table for displaying cart details.
    private DefaultTableModel tableModel; // Table model for the cartTable.
    // Labels for displaying total, discounts, and final total.
    private JLabel totalLabel;
    private JLabel firstPurchaseDiscountLabel;
    private JLabel threeItemsDiscountLabel;
    private JLabel finalTotalLabel;
    public CartGUI(List<Product> shopCartList) {
        this.shopCartList = shopCartList;
        initializeGUI();
        populateCartDetails();
    }
    //Initializes the graphical user interface components.
    private void initializeGUI() {
        setTitle("Shopping Cart");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Setting up the table and its model.
        String[] columnNames = {"Product", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        // Labels for displaying cart information.
        totalLabel = new JLabel("Total:");
        firstPurchaseDiscountLabel = new JLabel("First Purchase Discount (10%):");
        threeItemsDiscountLabel = new JLabel("Three Items in the Same Category Discount (20%):");
        finalTotalLabel = new JLabel("Final Total:");
        // Panel for organizing information labels.
        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.add(totalLabel);
        infoPanel.add(firstPurchaseDiscountLabel);
        infoPanel.add(threeItemsDiscountLabel);
        infoPanel.add(finalTotalLabel);
        // Main panel for layout organization.
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.SOUTH);
        // Adding the main panel to the frame.
        add(mainPanel);
    }
    //Populates the cart details in the table and updates information labels.
    private void populateCartDetails() {
        tableModel.setRowCount(0);
        double totalPrice = 0;
        // Populating table with cart details.
        if (shopCartList != null) {
            for (Product product : shopCartList) {
                tableModel.addRow(new Object[]{
                        product.getProductName(),
                        1,// Assuming quantity is always 1 for simplicity.
                        product.getPrice()
                });
                totalPrice += product.getPrice();
            }
        }
        // Updating information labels.
        totalLabel.setText("Total: $" + totalPrice);
        firstPurchaseDiscountLabel.setText("First Purchase Discount (10%): $" + (totalPrice * 0.1));
        threeItemsDiscountLabel.setText("Three Items in the Same Category Discount (20%): $" + (totalPrice * 0.2));
        finalTotalLabel.setText("Final Total: $" + calculateFinalTotal(totalPrice));
    }
    private double calculateFinalTotal(double totalPrice) {
        return totalPrice - (totalPrice * 0.1) - (totalPrice * 0.2);
    }
    public static void main(String[] args) {
        // Creating and setting up the shopping manager.
        WestminsterShoppingManager manager = new WestminsterShoppingManager();
        manager.loadProducts();
        WestminsterShoppingManager.openGUI();
        List<Product> shopCartList = manager.getShoppingList();
        // Launching the CartGUI in the event dispatch thread.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CartGUI(shopCartList).setVisible(true);
            }
        });
    }
}
