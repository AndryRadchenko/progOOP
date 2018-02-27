import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adddialog")
public class AddDialogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrdersDbDataAccessImpl ordersDAO = new OrdersDbDataAccessImpl();
        String parameter = req.getParameter("parameter");
        List<String> tableHeader = new ArrayList<>();

        switch (parameter){
            case "clients" :
                tableHeader.clear();
                tableHeader.add("CLIENT_NAME");
                tableHeader.add("CLIENT_LASTNAME");
                tableHeader.add("BIRTHDATE_YYYY-MM-DD");
                tableHeader.add("PHONE");
                req.setAttribute("parameter", "client");
                break;
            case "goods" :
                tableHeader.clear();
                tableHeader.add("GOOD_NAME");
                tableHeader.add("GOOD_QUANTITY_INT");
                tableHeader.add("PRICE_INT");
                req.setAttribute("parameter", "good");
                break;
            case "orders" :
                List<Client> clients = ordersDAO.viewClients();
                List<Good> goods = ordersDAO.viewGoods();
                tableHeader.clear();
                tableHeader.add("CLIENT");
                tableHeader.add("GOOD");
                tableHeader.add("GOOD_QUANTITY_INT");
                req.setAttribute("parameter", "order");
                req.setAttribute("clients", clients);
                req.setAttribute("goods", goods);
                break;
        }

        req.setAttribute("tableHeader", tableHeader );
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/add.jsp");
        dispatcher.forward(req, resp);
    }
}