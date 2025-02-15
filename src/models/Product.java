package models;

public class Product {
    private int id;
    private String name;
    private double price;
    private int count;
    private ProductCategory category; // Новое поле для категории

    public Product(int id, String name, double price, int count, ProductCategory category) {
        setId(id);
        setName(name);
        setPrice(price);
        setCount(count);
        setCategory(category);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}

//1