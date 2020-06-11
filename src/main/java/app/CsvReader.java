package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader
{
    private ArrayList<Product> products;

    CsvReader()
    {
        //Load products from csv file to products ArrayList
        System.out.println("Products from csv file: ");
        try
        {
            this.products = readProductsFromFile("products.csv");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        if (products != null)
            for (Product product : products)
                System.out.println(product.getId() + "," + product.getName() + "," +
                        product.getPrice() + "," + product.getCategory());

        //Adding products list from csv to DB - maybe exist problem with id (look method csvReader())
        //for (Product product : products) {
        //    putProductIntoDB(product);
        //}
    }



    static ArrayList<Product> readProductsFromFile(String path) throws  NumberFormatException, NullPointerException, IOException {
        if (path == null)
            throw new NullPointerException();

        ArrayList <Product> products = new ArrayList<>();
        String row;

        int id = 11; //nextid from db

        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        while ((row = csvReader.readLine()) != null) {
            Product product = new Product(0,"nazwa",0.00,"kategoria");

            String[] data = row.split(";");
            product.setId(id);
            product.setName(data[0]);
            product.setPrice(Double.parseDouble(data[1]));
            if(data[2] == "groceries" ||
                    data[2] == "preparedFood" ||
                    data[2] == "prescriptionDrug" ||
                    data[2] == "nonPrescriptionDrug" ||
                    data[2] == "clothing" ||
                    data[2] == "intangibles");
            product.setCategory(data[2]);
            id++;
            products.add(product);
        }

        csvReader.close();
        return products;
    }
}
