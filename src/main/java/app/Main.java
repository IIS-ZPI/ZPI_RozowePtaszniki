package app;

import app.util.Paths;
import com.google.gson.Gson;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
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

    private static String getProductsJsonString() throws URISyntaxException, SQLException{
        ArrayList<Product> allProducts = getProductsFromDB();
        Gson gson = new Gson();
        return gson.toJson(allProducts);
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    private static ArrayList<Product> getProductsFromDB() throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Produkty\";";
        ResultSet result = statement.executeQuery(query);
        ArrayList<Product> products = new ArrayList<>();

        while(result.next())
        {
            products.add(new Product(result.getInt("id"),result.getString("nazwa"),
                    result.getDouble("cena"),result.getString("kategoria")));
        }
        return products;
    }

    // prototype of adding new products function (right now not in use)
    private static void putProductIntoDB(Product pr) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "INSERT INTO public.\"Produkty\" ('id','nazwa', 'cena','kategoria') VALUES(" +
                      pr.getId() + "," +
                "'" + pr.getNazwa() + "'," +
                      pr.getCena() + "," +
                "'" + pr.getKategoria() + "');";
        statement.executeQuery(query);
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
