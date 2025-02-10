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
        setId(id);
        setUserId(userId);
        setProductId(productId);
        setQuantity(quantity);
        setTotalPrice(totalPrice);
        setStatus(status);
    }

    public Order(int id, int userId, int productId, int quantity, double totalPrice, String status, String userName, String productName) {
        setId(id);
        setUserId(userId);
        setProductId(productId);
        setQuantity(quantity);
        setTotalPrice(totalPrice);
        setStatus(status);
        setUserName(userName);
        setProductName(productName);
    }

    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setProductId(int productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(String status) { this.status = status; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public String getUserName() { return userName; }
    public String getProductName() { return productName; }
}
