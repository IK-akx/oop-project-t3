package models;

public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;
    private String status;
    private String userName;
    private String productName;

    public Order(int id, int userId, int productId, int quantity, double totalPrice, String status) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order(int id, int userId, int productId, int quantity, double totalPrice, String status, String userName, String productName) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.userName = userName;
        this.productName = productName;
    }

    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(String status) { this.status = status; }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
}
