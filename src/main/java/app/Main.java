package app;

import app.util.Paths;
import com.google.gson.Gson;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
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

        /*Load products from csv file to products ArrayList*/
        ArrayList<Product> products = null;
        System.out.println("Products from csv file: ");
        try {
            products = csvReader("products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (products != null)
            for (Product product : products) {
                System.out.println(product.getId() + "," + product.getNazwa() + "," + product.getCena() + "," + product.getKategoria());
            }

        /*Adding products list from csv to DB - maybe exist problem with id (look method csvReader()) */
        for (Product product : products) {
            //putProductIntoDB(product);

        }
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
        Database db = new Database();
        ArrayList<Product> allProducts = db.getProductsFromDB();
        Gson gson = new Gson();
        return gson.toJson(allProducts);
    }

<<<<<<< HEAD
=======
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

    static ArrayList <Product> csvReader(String path) throws IOException {
        ArrayList <Product> products = new ArrayList<>();
        String row;

        int id = 11; //nextid from db

        BufferedReader csvReader = new BufferedReader(new FileReader(path));
        while ((row = csvReader.readLine()) != null) {
            Product product = new Product(0,"nazwa",0.00,"kategoria");

            String[] data = row.split(";");
            product.setId(id);
            product.setNazwa(data[0]);
            product.setCena(Double.parseDouble(data[1]));
            if(data[2] == "groceries" ||
                    data[2] == "preparedFood" ||
                    data[2] == "prescriptionDrug" ||
                    data[2] == "nonPrescriptionDrug" ||
                    data[2] == "clothing" ||
                    data[2] == "intangibles");
            product.setKategoria(data[2]);
            id++;
            products.add(product);
        }

        csvReader.close();
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

>>>>>>> 023917f7373ed36fec8bf7009e772fa90db6d269
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
