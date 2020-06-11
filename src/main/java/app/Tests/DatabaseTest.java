package app.Tests;

import app.Database;
import app.Product;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    private Database database = new Database();
    private ArrayList<Product> products = new ArrayList<>();

    @Test
    void getConnection() {

        try {
            Connection con = database.getConnection();
        } catch (URISyntaxException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getProductById() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getProductsFromDB() {
        try {
            products = Database.getProductsFromDB();
            //System.out.println(products);
        } catch (URISyntaxException | NullPointerException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void putProductIntoDB(){
        Product pr = new Product(98667,"Milk", 0.98, "preparedFood");
        try {
            database.putProductIntoDB(pr);

            ArrayList<Product> pr2 = new ArrayList<>();

            pr2 = database.getProductById(pr.getId());

            assertEquals(pr, pr2.get(0));

            database.deleteProductById(pr.getId());


        } catch (URISyntaxException | NullPointerException | SQLException e) {
            e.printStackTrace();
        }


    }
}