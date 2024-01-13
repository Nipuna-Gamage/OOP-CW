// Subclass 2
public class Clothing extends Product {
    private String size;
    private String color;
    // Constructor
    public Clothing(String productID, String productName, int availableItems, double price, String size, String color) {
        super(productID, productName, availableItems, price);
        this.size=size;
        this.color=color;
    }
    /// Getter and setter methods for Clothing-specific attributes
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
