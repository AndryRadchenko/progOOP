import java.sql.*;
import java.util.Random;

public class DbPopulate {
    private static final String[] DISTRICTS = {"Звездных Войн", "Чужих"};
    private static final String[][] STREETS_PACK= {
            {"Люка Скайуокера улица", "Темной Стороны Силы площадь", "Дарта Вейдера улица"},
            {"Лейтенанта Рипли площадь", "Ксеноморфная улица", "Инженерный переулок"}
    };

    private static final int FLATS_NUMBER = 20;
    private static final int MAX_ROOMS = 3;

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
            st.execute("DROP TABLE IF EXISTS apartments");
            st.execute("CREATE TABLE apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "district VARCHAR(20)," +
                    "address VARCHAR(50)," +
                    "rooms INT," +
                    "area INT," +
                    "price INT)");
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillTables(){

        Random rnd = new Random();

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement("INSERT INTO apartments " +
                     "(district, address, rooms, area, price) VALUES(?, ?, ?, ?, ?)");
        ) {

            for (int i = 0; i < FLATS_NUMBER; i++) {
                int districtNumber = rnd.nextInt(DISTRICTS.length);
                String[] streets = STREETS_PACK[districtNumber];
                int roomsNumber = rnd.nextInt(MAX_ROOMS) + 1;
                int area = roomsNumber * 22 + rnd.nextInt(10);
                int price = area * 1000 + rnd.nextInt(20) * 1000;

                ps.setString(1, DISTRICTS[districtNumber]);
                ps.setString(2, streets[rnd.nextInt(3)]);
                ps.setInt(3, roomsNumber);
                ps.setInt(4, area);
                ps.setInt(5, price);
                ps.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
