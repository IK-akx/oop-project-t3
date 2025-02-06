package models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int count;

    public Product(int id, String name, double price, int count) {
        setId(id);
        setName(name);
        setPrice(price);
        setCount(count);
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCount(int count) { this.count = count; }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getCount() { return count; }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: " + price + ", Count: " + count;
    }
}
