package app;

import app.util.Paths;
import com.google.gson.Gson;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws URISyntaxException, SQLException {
        ProfitCalculator profitCalculator = new ProfitCalculator();
        //System.out.println(profitCalculator.CalculateForAllStates("Jabłko 0.2 groceries 10"));

        staticFiles.location("/public");

        port(getHerokuAssignedPort());

        get(Paths.Web.START_PAGE, (req, res) -> renderContent(Paths.Template.INDEX));

        get(Paths.Web.PRODUCTS, (req, res) -> getProductsJsonString());

        get(Paths.Web.CALCULATE, (req, res) -> {
            String dumbString = req.params(":product") + " " + req.params(":cost") + " " + req.params(":category") + " " + req.params(":final_cost"); // this needs to be changed @Leonard
            return profitCalculator.CalculateForAllStates(dumbString);
        });
    }

    private static String renderContent(String htmlFile) {
        String htmlString = null;
        try {
            htmlString = IOUtils.toString(Spark.class.getResourceAsStream(htmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlString;
    }

    private static String getProductsJsonString(){
        ArrayList<Product> allProducts = getAllProducts();
        Gson gson = new Gson();
        return gson.toJson(allProducts);
    }

    private static ArrayList<Product> getAllProducts(){
        // This should return all products from database in a form of ArrayList but it's currently hardcoded instead.
        ArrayList<Product> allProducts = new ArrayList<>();

        allProducts.add(new Product(0, "Ibuprom", 4.99, "prescriptionDrug"));
        allProducts.add(new Product(1, "Aspiryna", 29.99, "nonPrescriptionDrug"));
        allProducts.add(new Product(2, "Jabłko", 0.2, "groceries"));

        return allProducts;
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
