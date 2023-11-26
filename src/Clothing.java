class Clothing extends Product {
    private String size;
    private String color;
    public Clothing(String productID, String productName, int availableItems, double price) {
        super(productID, productName, availableItems, price);
    }
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
