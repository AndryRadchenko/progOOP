import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrdersDbDataAccessImpl ordersDAO = new OrdersDbDataAccessImpl();
        String parameter = req.getParameter("parameter");


        switch (parameter){
            case "client" :
                try {
                    Client client = new Client();
                    client.setName(req.getParameter("name"));
                    client.setLastName(req.getParameter("lastname"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    client.setBirthDate(sdf.parse(req.getParameter("birthdate")));
                    client.setPhone(req.getParameter("phone"));
                    ordersDAO.addClient(client);
                } catch (ParseException e){
                }
                break;
            case "good" :
                Good good = new Good();
                good.setName(req.getParameter("name"));
                good.setQuantity(Integer.parseInt(req.getParameter("quantity")));
                good.setPrice(Integer.parseInt(req.getParameter("price")));
                ordersDAO.addGood(good);
                break;
            case "order" :
                Order order = new Order();
                order.setClientId(Integer.parseInt(req.getParameter("clientid")));
                order.setGoodId(Integer.parseInt(req.getParameter("goodid")));
                order.setGoodQuantity(Integer.parseInt(req.getParameter("quantity")));
                ordersDAO.addOrder(order);
                break;
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
        dispatcher.forward(req, resp);
    }
}