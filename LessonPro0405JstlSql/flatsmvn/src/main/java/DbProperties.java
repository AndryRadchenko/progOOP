import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private String sqlurl;
    private String url;
    private String user;
    private String password;
    private String name;

    public DbProperties(){
        InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties");

        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        sqlurl = props.getProperty("sqlurl");
        url = props.getProperty("sqlurl")+props.getProperty("name");
        user = props.getProperty("user");
        password = props.getProperty("password");
        name = props.getProperty("name");
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSqlurl() {
        return sqlurl;
    }
}
