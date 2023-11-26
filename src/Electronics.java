class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;
    public Electronics(String productID, String productName, int availableItems, double price) {
        super(productID, productName, availableItems, price);
    }
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
