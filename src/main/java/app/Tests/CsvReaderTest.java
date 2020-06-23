package app.Tests;

import app.CsvReader;
import app.Product;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    @Test
    void readProductsFromFile() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"adidas Originals Gazelle Trainers",110.00,"clothing");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(8).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile1() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(11,"Blue Colourblock Poloshirt",25.99,"clothing");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(0).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile2() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Blue Stretch Tonic Suit: Waistcoat",49.90,"clothing");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(4).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile3() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Blue Ribbon Classics Fudge Bar , 20pk",4.97,"groceries");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(13).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile4() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Celery, each",0.98,"groceries");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(17).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile5() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Asparagus, Bunch",2.97,"groceries");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(21).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile6() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Amy's Frozen Bowls, Chile Relleno Casserole, Gluten Free, Non GMO, 9-Ounce",3.98,"preparedFood");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(34).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile7() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"MS Visio 2019 Professional Product Key 32/64 bit license key",65.50,"intangibles");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(53).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile8() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Vibin mini, 0.02 mg + 3 mg, coated tablets, 28 ct",5.80,"prescriptionDrug");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(61).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void readProductsFromFile9() {
        ArrayList<Product> products;

        CsvReader csvReader = new CsvReader();
        Product p1 = new Product(16,"Norton 360 Deluxe  Antivirus software for 5 Devices with Auto Renewal",24.99,"intangibles");
        try {
            products = csvReader.readProductsFromFile("products.csv");
            assertEquals(p1.getName(), products.get(54).getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}