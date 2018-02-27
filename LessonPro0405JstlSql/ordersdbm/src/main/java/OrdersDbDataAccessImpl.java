import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDbDataAccessImpl implements OrdersDbDataAccess {

    private static final DbProperties PROPS = new DbProperties();

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    static {
        try{
            Class.forName(DATABASE_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFound");
        }

// СОЗДАНИЕ И ЗАПОЛНЕНИЕ БД
        DbPopulate.populate();

    }

    public void addGood(Good good){
        String query = "INSERT INTO goods (name, quantity, price) VALUES (?, ?, ?);";
        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            con.setAutoCommit(false);
            ps.setString(1, good.getName());
            ps.setInt(2, good.getQuantity());
            ps.setInt(3, good.getPrice());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addClient(Client client){
        String query = "INSERT INTO clients (name, lastname, birthdate, phone) VALUES (?, ?, ?, ?);";
        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(query)
        ) {
            con.setAutoCommit(false);
            ps.setString(1, client.getName());
            ps.setString(2, client.getLastName());
            ps.setDate(3, new Date(client.getBirthDate().getTime()));
            ps.setString(4, client.getPhone());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addOrder (Order order){
        int goodsAvailable = getGoodQuantityById(order.getGoodId());
        if (order.getGoodQuantity() > goodsAvailable){
            System.out.println("Not enough goods");
        } else {
            String newOrderQuery = "INSERT INTO orders (client_id , good_id , good_quantity ) VALUES ( ?, ?, ?);";
            String updateGoodsQuery = "UPDATE goods SET quantity=? WHERE id=?;";

            try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
                 PreparedStatement psOne = con.prepareStatement(newOrderQuery);
                 PreparedStatement psTwo = con.prepareStatement(updateGoodsQuery);
            ) {
                con.setAutoCommit(false);
                psOne.setInt(1, order.getClientId());
                psOne.setInt(2, order.getGoodId());
                psOne.setInt(3, order.getGoodQuantity());
                psOne.executeUpdate();
                psTwo.setInt(1, goodsAvailable - order.getGoodQuantity());
                psTwo.setInt(2, order.getGoodId());
                psTwo.executeUpdate();
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private int getGoodQuantityById(int id){
        String goodsQuery = "SELECT quantity FROM goods WHERE id=" + id + ";";
        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(goodsQuery);
             ResultSet rs = ps.executeQuery();
        ){
            rs.next();
            int goodQuantity = rs.getInt(1);
            return goodQuantity;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Good> viewGoods(){
        List<Good> goodsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement("SELECT * FROM goods;");
             ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Good good = new Good();
                good.setId(rs.getInt(1));
                good.setName(rs.getString(2));
                good.setQuantity(rs.getInt(3));
                good.setPrice(rs.getInt(4));
                goodsList.add(good);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return goodsList;
    }

    public List<Client> viewClients(){
        List<Client> clientsList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement("SELECT * FROM clients;");
             ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt(1));
                client.setName(rs.getString(2));
                client.setLastName(rs.getString(3));
                client.setBirthDate(rs.getDate(4));
                client.setPhone(rs.getString(5));
                clientsList.add(client);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return clientsList;
    }

    public List<OrderJoined> viewOrders(){
        List<OrderJoined> ordersJoinedList = new ArrayList<>();
        String query = "SELECT orders.id, clients.name, clients.lastname, orders.good_id, orders.good_quantity " +
                "FROM clients INNER JOIN orders ON (orders.client_id=clients.id) ORDER BY orders.id;";

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                OrderJoined orderJ = new OrderJoined();
                orderJ.setNumber(rs.getInt(1));
                orderJ.setClientName(rs.getString(2));
                orderJ.setClientLastName(rs.getString(3));
                orderJ.setGoodId(rs.getInt(4));
                orderJ.setGoodQuantity(rs.getInt(5));
                ordersJoinedList.add(orderJ);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return ordersJoinedList;
    }
}
