package models;

public class Order {
    private int id;
    private String product;
    private int quantity;
    private String status; // "Pending", "Completed", etc.

    public Order(int id, String product, int quantity, String status) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }


    public int getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

