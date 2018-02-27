import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/filter")
public class NewHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        FlatsDataAccessImpl flatsDAO = new FlatsDataAccessImpl();

        String dbQuery = flatsDAO.filteredQueryConstructor(req);

        Flats flats = new Flats();
        flats.setFlatsList(flatsDAO.getFlats(dbQuery));

        req.setAttribute("flatsList", flats.getFlatsList());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}