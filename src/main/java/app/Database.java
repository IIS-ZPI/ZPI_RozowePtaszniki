package app;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class Database
{
    public Database(){};

    public static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static ArrayList<Product> getProductById(int id) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Produkty\" WHERE id=" + String.valueOf(id) + ";";
        ResultSet result = statement.executeQuery(query);
        ArrayList<Product> products = new ArrayList<>();

        while(result.next())
        {
            products.add(new Product(result.getInt("id"),result.getString("nazwa"),
                    result.getDouble("cena"),result.getString("kategoria")));
        }

        return products;
    }

    public static ArrayList<Product> getProductsFromDB() throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Produkty\";";
        ResultSet result = statement.executeQuery(query);
        ArrayList<Product> products = new ArrayList<>();

        while(result.next()) {
            products.add(new Product(result.getInt("id"),result.getString("nazwa"),
                    result.getDouble("cena"),result.getString("kategoria")));
        }

        return products;
    }
//
//    // prototype of adding new products function (right now not in use)
    public static void putProductIntoDB(Product pr) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "INSERT INTO public.\"Produkty\" ('id','nazwa', 'cena','kategoria') VALUES(" +
               pr.getId() + "," +
                "'" + pr.getName() + "'," +
               pr.getPrice() + "," +
                "'" + pr.getCategory() + "');";
        statement.executeQuery(query);
    }

    public void deleteProductById(int id) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "DELETE from public.\"Produkty\" WHERE id=" + String.valueOf(id) + ";";
        ResultSet result = statement.executeQuery(query);
    }
}
