import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlatsDataAccessImpl implements FlatsDataAccess {

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

    public List<Flat> getFlats(String DbQuery)  {
        List<Flat> flatList = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(DbQuery);
             ResultSet rs = ps.executeQuery();
        ) {
                ResultSetMetaData md = rs.getMetaData();

                while (rs.next()) {
                    Flat newFlat = new Flat();
                    newFlat.setId(rs.getInt(1));
                    newFlat.setDistrict(rs.getString(2));
                    newFlat.setStreet(rs.getString(3));
                    newFlat.setRooms(rs.getInt(4));
                    newFlat.setArea(rs.getInt(5));
                    newFlat.setPrice(rs.getInt(6));
                    flatList.add(newFlat);

//                    for (int i = 1; i <= md.getColumnCount(); i++) {
//                        System.out.print(rs.getString(i) + "\t\t\t");
//                    }
//                    System.out.println();
                }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return flatList;
    }

    public List<String> getDistinctStringFields (String parameter){
        String dbQuery = "SELECT DISTINCT " + parameter + " FROM apartments";
        List<String> rowData = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(dbQuery);
             ResultSet rs = ps.executeQuery();
        ){
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next())
                rowData.add(rs.getString(1));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return rowData;
    }

    public int getMinInteger (String parameter){
        String dbQuery = "SELECT MIN(" + parameter + ") FROM apartments";

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(dbQuery);
             ResultSet rs = ps.executeQuery();
        ){
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public int getMaxInteger (String parameter){
        String dbQuery = "SELECT MAX(" + parameter + ") FROM apartments";

        try (Connection con = DriverManager.getConnection(PROPS.getUrl(), PROPS.getUser(), PROPS.getPassword());
             PreparedStatement ps = con.prepareStatement(dbQuery);
             ResultSet rs = ps.executeQuery();
        ){
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public String filteredQueryConstructor(HttpServletRequest req){
        String district = req.getParameter("district");
        String address = req.getParameter("street");
        String areaFromStr = req.getParameter("areafrom");
        String areaToStr = req.getParameter("areato");
        String roomsFromStr = req.getParameter("roomsfrom");
        String roomsToStr = req.getParameter("roomsto");
        String priceFromStr = req.getParameter("pricefrom");
        String priceToStr = req.getParameter("priceto");

        String[] queryElements = new String[8];
        queryElements[0] = district == null || district.equals("") ? "" : "district  = \'" + district + "\'";
        queryElements[1] = address == null || address.equals("") ? "" : "address  = \'" + address + "\'";
        queryElements[2] = areaFromStr == null || areaFromStr.equals("") ? "" : "area >= " + areaFromStr;
        queryElements[3] = areaToStr == null || areaToStr.equals("") ? "" : "area <= " + areaToStr;
        queryElements[4] = roomsFromStr == null || roomsFromStr.equals("") ? "" : "rooms >= " + roomsFromStr;
        queryElements[5] = roomsToStr == null || roomsToStr.equals("") ? "" : "rooms <= " + roomsToStr;
        queryElements[6] = priceFromStr == null || priceFromStr.equals("") ? "" : "price >= " + priceFromStr;
        queryElements[7] = priceToStr == null || priceToStr.equals("") ? "" : "price <= " + priceToStr;

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM apartments");
        boolean isInitiallyAppended = true;

        for (String element : queryElements) {
            if (!element.equals("")) {
                if (isInitiallyAppended == true) {
                    sb.append(" WHERE ");
                    isInitiallyAppended = false;
                }
                else
                    sb.append(" AND ");
                sb.append(element);
            }
        }
        return sb.toString();
    }


}
