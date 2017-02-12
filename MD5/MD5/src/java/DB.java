
/**
 *
 * @author GaNeShKuMaRm
 */
import java.sql.*;
public class DB {
    public String register(String name, String email, String md5Password)
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/GaNeShKuMaRm/Documents/md5.accdb");
            PreparedStatement statement = con.prepareStatement("INSERT INTO users(names, email, password) values(?,?,?)");
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, md5Password);
            statement.executeUpdate();
            con.commit();
            return "success";
        }
        catch(Exception e)
        {
            return e.toString();
        }
    }
    public String login(String email, String md5Password)
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/GaNeShKuMaRm/Documents/md5.accdb");
            PreparedStatement statement = con.prepareStatement("SELECT names FROM users WHERE email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, md5Password);
            ResultSet rs = statement.executeQuery();
            String name = "$$$$$";
            while(rs.next())
            {
                name = rs.getString("names");
                return name;
            }
            return name;
        }
        catch(Exception e)
        {
            return e.toString();
        }
    }
    public static void main(String args[])
    {
        //register("a", "b", "c");
    }
}
