// Subclass 1
public class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;
    // Constructor
    public Electronics(String productID, String productName, int availableItems, double price, String brand, int warrantyPeriod) {
        super(productID, productName, availableItems, price);
        this.brand=brand;
        this.warrantyPeriod=warrantyPeriod;
    }
    // Getter and setter methods for Electronics-specific attributes
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
