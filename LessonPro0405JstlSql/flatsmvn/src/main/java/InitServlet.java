import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/initial")
public class InitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//      объявление и инициализация наборов опций и полей фильтра
        FlatsDataAccessImpl flatsDAO = new FlatsDataAccessImpl();
        List <String> districtList = (flatsDAO.getDistinctStringFields("district"));
        List <String> streetList = (flatsDAO.getDistinctStringFields("address"));
        int roomsMin = flatsDAO.getMinInteger("rooms");
        int roomsMax = flatsDAO.getMaxInteger("rooms");
        int areaMin = flatsDAO.getMinInteger("area");
        int areaMax = flatsDAO.getMaxInteger("area");
        int priceMin = flatsDAO.getMinInteger("price");
        int priceMax = flatsDAO.getMaxInteger("price");

        req.setAttribute("districtList", districtList);
        req.setAttribute("streetList", streetList);
        req.setAttribute("roomsMin", roomsMin);
        req.setAttribute("roomsMax", roomsMax);
        req.setAttribute("areaMin", areaMin);
        req.setAttribute("areaMax", areaMax);
        req.setAttribute("priceMin", priceMin);
        req.setAttribute("priceMax", priceMax);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/options.jsp");
        dispatcher.forward(req, resp);
    }
}









