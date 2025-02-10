package models;

public class Customer extends User {
    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + ", isAdmin: " + this.isAdmin();
    }
}

//1