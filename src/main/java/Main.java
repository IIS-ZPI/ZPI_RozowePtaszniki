import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

import static spark.Spark.*;

public class Main {

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
/*
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("/src/hibernate.cfg.xml").applySettings(
                configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Produkt(10,"Banan", 0.6,"Owoce"));
        session.getTransaction().commit();
*/

    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public static void main(String[] args) throws URISyntaxException, SQLException {

        port(getHerokuAssignedPort());

        get("/", (req, res) -> getDataFromDB());

        get("/hello", (req, res) -> "Siemanko");
        //get("/", (req, res) -> "pierwsza strona aby odblokowac druga dodaj /hello");
        //System.out.println(getNameFromDB());



    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
