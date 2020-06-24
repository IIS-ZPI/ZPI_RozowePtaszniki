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
        //Statement statement = conn.createStatement();
        //String query = "SELECT * from public.\"Produkty\" WHERE id=" + String.valueOf(id) + ";";
        //ResultSet result = statement.executeQuery(query);
        ArrayList<Product> products = new ArrayList<>();

        PreparedStatement statement = conn.prepareStatement("SELECT * from public.\"Produkty\" WHERE id= ?");

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();

        while(result.next())
        {
            products.add(new Product(result.getInt("id"),result.getString("nazwa"),
                    result.getDouble("cena"),result.getString("kategoria")));
        }

        conn.close();

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

        conn.close();

        return products;
    }

    public static ArrayList<TaxesInState> getTaxesFromDB() throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"Taxes\";";
        ResultSet result = statement.executeQuery(query);
        ArrayList<TaxesInState> taxes = new ArrayList<>();

        while(result.next()) {
            taxes.add(new TaxesInState(result.getString("stateName"),result.getDouble("baseTax"),result.getDouble("maxLocalSurtax"),
                result.getString("groceries"),result.getString("preparedFood"),result.getString("prescriptionDrug"),
                    result.getString("nonPrescriptionDrug"),result.getString("clothing"),result.getString("intangibles"),
                        result.getDouble("commonCosts")));
        }

        conn.close();

        return taxes;
    }

    public static ArrayList<InterCosts> getInternationalCostsFromDB() throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        Statement statement = conn.createStatement();
        String query = "SELECT * from public.\"InternationalTransportationCosts\";";
        ResultSet result = statement.executeQuery(query);
        ArrayList<InterCosts> interFees = new ArrayList<>();

        while(result.next()) {
            interFees.add(new InterCosts(result.getString("stateName"),result.getString("currency"),result.getDouble("transportFee"),
                    result.getDouble("importTarifCons"),result.getDouble("importTarifOther")));
        }

        conn.close();

        return interFees;
    }
//
//    // prototype of adding new products function (right now not in use)
    public static void putProductIntoDB(Product pr) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        /*Statement statement = conn.createStatement();
        String query = "INSERT INTO public.\"Produkty\" ('id','nazwa', 'cena','kategoria') VALUES(" +
               pr.getId() + "," +
                "'" + pr.getName() + "'," +
               pr.getPrice() + "," +
                "'" + pr.getCategory() + "');";
        statement.executeQuery(query);*/
        PreparedStatement statement = conn.prepareStatement("INSERT INTO public.\"Produkty\" ('id','nazwa', 'cena','kategoria') VALUES(?,?,?,?)");

        statement.setInt(1, pr.getId());
        statement.setString(2, pr.getName());
        statement.setDouble(3, pr.getPrice());
        statement.setString(4, pr.getCategory());


        ResultSet result = statement.executeQuery();
        conn.close();
    }

    public void deleteProductById(int id) throws URISyntaxException, SQLException {
        Connection conn = getConnection();
        //Statement statement = conn.createStatement();
        //String query = "DELETE from public.\"Produkty\" WHERE id=" + String.valueOf(id) + ";";
        //ResultSet result = statement.executeQuery(query);

        PreparedStatement statement = conn.prepareStatement("DELETE from public.\"Produkty\" WHERE id = ?");

        statement.setInt(1, id);

        ResultSet result = statement.executeQuery();
        conn.close();
    }
}
