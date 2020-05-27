package app;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

public class Database
{
    Database(){};

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static Product getProductById(int id) throws URISyntaxException, SQLException {
        System.out.println("id");
        System.out.println(id);
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Produkty\" WHERE id=" + Integer.toString(id) + ";";
        System.out.println(query);
        ResultSet result = statement.executeQuery(query);
        return new Product(
                result.getInt("id"),
                result.getString("nazwa"),
                result.getDouble("cena"),
                result.getString("kategoria")
        );
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
//    public static void putProductIntoDB(Product pr) throws URISyntaxException, SQLException {
//        Connection conn = getConnection();
//        Statement statement = conn.createStatement();
//        String query = "INSERT INTO public.\"Produkty\" ('id','nazwa', 'cena','kategoria') VALUES(" +
//                pr.getId() + "," +
//                "'" + pr.getNazwa() + "'," +
//                pr.getCena() + "," +
//                "'" + pr.getKategoria() + "');";
//        statement.executeQuery(query);
//    }
}
