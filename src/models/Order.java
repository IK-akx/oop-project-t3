package models;

public class Order {
    private int id;
    private String product;
    private int quantity;
    private String status; // "Pending", "Completed", etc.

    public Order(int id, String product, int quantity, String status) {
        setId(id);
        setProduct(product);
        setQuantity(quantity);
        setSaleStatus(status);
    }

    public void setId(int id) {this.id = id;}
    public void setProduct(String product) {this.product = product;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setSaleStatus(String status) {this.status = status;}

    public int getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

