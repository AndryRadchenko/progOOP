import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FlatsDataAccess {

    List<Flat> getFlats(String DbQuery);
    List<String> getDistinctStringFields(String parameter);
    int getMinInteger(String parameter);
    int getMaxInteger(String parameter);
    String filteredQueryConstructor(HttpServletRequest req);

}
