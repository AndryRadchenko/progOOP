import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbPopulate {
    private static final DbProperties PROPS = new DbProperties();
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    public static void populate() {
        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound");
        }

        dbInit();
        createTables();
        fillTables();
    }

    private static void dbInit() {
        try (Connection con = DriverManager.getConnection(PROPS.getSqlurl(), PROPS.getUser(), PROPS.getPassword());
             Statement st = con.createStatement();
        ) {
            st.execute("DROP DATABASE IF EXISTS " + PROPS.getName() + ";");
            st.execute("CREATE DATABASE " + PROPS.getName() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() {
        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             Statement st = con.createStatement();
        ) {
            con.setAutoCommit(false);
            st.execute("DROP TABLE IF EXISTS goods;");
            st.execute("DROP TABLE IF EXISTS clients;");
            st.execute("DROP TABLE IF EXISTS orders;");
            st.execute("CREATE TABLE goods (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "quantity INT NOT NULL, " +
                    "price INT NOT NULL" +
                    ");");
            st.execute("CREATE TABLE clients (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "lastname VARCHAR(20) NOT NULL, " +
                    "birthdate DATE NOT NULL, " +
                    "phone VARCHAR(20) NOT NULL" +
                    ");");
            st.execute("CREATE TABLE orders (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "client_id INT NOT NULL, " +
                    "good_id INT NOT NULL, " +
                    "good_quantity INT NOT NULL," +
                    "FOREIGN KEY (client_id) REFERENCES clients(id)," +
                    "FOREIGN KEY (good_id) REFERENCES goods(id)" +
                    ");");
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillTables(){
        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             Statement st = con.createStatement();
        ) {
            con.setAutoCommit(false);
//          СОЗДАНИЕ КЛИЕНТОВ
            st.execute("INSERT INTO clients (name, lastname, birthdate, phone) VALUES ('Neo', 'TheChosenOne', '0001-01-01', '0000000');");
            st.execute("INSERT INTO clients (name, lastname, birthdate, phone) VALUES ('Morpheus', 'Black', '2000-01-01', '111111111');");
            st.execute("INSERT INTO clients (name, lastname, birthdate, phone) VALUES ('Trinity', 'Lady', '2018-02-16', '555555555');");
//          СОЗДАНИЕ КЛИЕНТОВ
            st.execute( "INSERT INTO goods (name, quantity, price) VALUES ('AMD Phenom II x4 965', 100, 30);");
            st.execute( "INSERT INTO goods (name, quantity, price) VALUES ('Intel Core i9', 900, 1200);");
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
        new OrdersDbDataAccessImpl().addOrder(new Order(1,1, 1, 2));
    }
}
