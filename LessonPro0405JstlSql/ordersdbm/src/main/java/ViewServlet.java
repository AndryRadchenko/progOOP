import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/view")
public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrdersDbDataAccessImpl ordersDAO = new OrdersDbDataAccessImpl();
        String parameter = req.getParameter("parameter");
        List<String> tableHeader = new ArrayList<>();

        switch (parameter){
            case "clients" :
                List<Client> clientsList = ordersDAO.viewClients();
                tableHeader.clear();
                tableHeader.add("ID");
                tableHeader.add("NAME");
                tableHeader.add("LASTNAME");
                tableHeader.add("BIRTHDATE");
                tableHeader.add("PHONE");
                req.setAttribute("clientsList", clientsList);
                break;
            case "goods" :
                List<Good> goodsList = ordersDAO.viewGoods();
                tableHeader.clear();
                tableHeader.add("ID");
                tableHeader.add("NAME");
                tableHeader.add("QUANTITY");
                tableHeader.add("PRICE");
                req.setAttribute("goodsList", goodsList);
                break;
            case "orders" :
                List<OrderJoined> ordersList = ordersDAO.viewOrders();
                tableHeader.clear();
                tableHeader.add("ORDER_ID");
                tableHeader.add("NAME");
                tableHeader.add("LAST_NAME");
                tableHeader.add("GOOD_ID");
                tableHeader.add("GOOD_QUANTITY");
                req.setAttribute("ordersList", ordersList);
                break;
        }

        req.setAttribute("tableHeader", tableHeader );
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/view.jsp");
        dispatcher.forward(req, resp);
    }
}