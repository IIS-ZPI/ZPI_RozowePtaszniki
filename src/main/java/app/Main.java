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

        staticFiles.location("/public");

        port(getHerokuAssignedPort());

        get(Paths.Web.START_PAGE, (req, res) -> renderContent(Paths.Template.INDEX));

        get(Paths.Web.PRODUCTS, (req, res) -> getProductsJsonString());

        get(Paths.Web.CALCULATE, (req, res) -> {
            String dumbString = req.params(":product") + " " + req.params(":cost") + " " + req.params(":category") + " " + req.params(":final_cost");
            ArrayList<ProfitData> profits = profitCalculator.Calculate(dumbString);
            return getProfitDataJsonString(profits);
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

    private static String getProfitDataJsonString(ArrayList<ProfitData> profits){
        Gson gson = new Gson();
        return gson.toJson(profits);
    }

    private static String getProductsJsonString(){
        ArrayList<Product> allProducts = getAllProducts();
        Gson gson = new Gson();
        return gson.toJson(allProducts);
    }

    private static ArrayList<Product> getAllProducts(){
        // This should return all products from database in a form of ArrayList but it's currently hardcoded instead.
        ArrayList<Product> allProducts = new ArrayList<>();

        allProducts.add(new Product(0, "Ibuprom", 4.99, "Leki"));
        allProducts.add(new Product(1, "Aspiryna", 29.99, "Leki"));
        allProducts.add(new Product(2, "Jabłko", 0.2, "Owoce"));

        return allProducts;
    }

/*
    private static String getDataFromDB() throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Produkty\";";
        ResultSet result = statement.executeQuery(query);
        String line = "";
        while(result.next())
        {
            line = line + result.getInt("id") + " " + result.getString("nazwa") + " "
                    + result.getDouble("cena")+ " " + result.getString("kategoria") +"\n";
            System.out.println(line);
        }
        return line;
    }
*/

/*
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/src/hibernate.cfg.xml").applySettings(
                configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new app.Produkt(10,"Banan", 0.6,"Owoce"));
        session.getTransaction().commit();
*/

/*
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }
*/

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
