import java.util.List;

public interface OrdersDbDataAccess {

    void addGood(Good good);
    void addClient(Client client);
    void addOrder(Order order);

    List<Good> viewGoods();
    List<Client> viewClients();
    List<OrderJoined> viewOrders();

}
