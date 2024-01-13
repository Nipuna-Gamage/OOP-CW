public abstract class Product {
    private String productID;
    private String productName;
    private int availableItems;
    private double price;
    // Constructor
    public Product(String productID, String productName, int availableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
    }
    // Getter and setter methods
    public String getProductID() {
        return productID;
    }
    public String getProductName() {
        return productName;
    }
    public int getAvailableItems() {
        return availableItems;
    }
    public double getPrice() {
        return price;
    }
}
