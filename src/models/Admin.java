package models;

public class Admin extends User {
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", isAdmin: " + this.isAdmin();
    }
}

//1