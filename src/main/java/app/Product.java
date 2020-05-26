package app;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product
{
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name="nazwa", nullable = false)
    private String name;

    @Column(name="kategoria", nullable = false)
    private String category;

    @Column(name="cena", nullable = false)
    @SerializedName("cena podstawowa")
    private double price;

    Product(int id, String name, double price, String category)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}