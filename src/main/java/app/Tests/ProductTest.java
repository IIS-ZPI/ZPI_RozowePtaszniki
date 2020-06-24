package app.Tests;

import app.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getId() {
        Product product = new Product(0,"Apple", 11.90, "groceries");
        assertEquals(product.getId(), 0);
    }

    @Test
    void getName() {
        Product product = new Product(0,"Apple", 11.90, "groceries");
        assertEquals(product.getName(), "Apple");
    }

    @Test
    void getCategory() {
        Product product = new Product(0,"Apple", 11.90, "groceries");
        assertEquals(product.getCategory(), "groceries");
    }

    @Test
    void getPrice() {
        Product product = new Product(0,"Apple", 11.90, "groceries");
        assertEquals(product.getPrice(), 11.90);
    }
}