import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBUtil {
    public static String url;
    public static String username;
    public static String password;

    static {
        try {
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("db.properties");
            props.load(fis);

            url = props.getProperty("db.url");
            username = props.getProperty("db.username");
            password = props.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
