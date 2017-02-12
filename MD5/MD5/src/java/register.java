
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GaNeShKuMaRm
 */
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            MD5HASH md5 = new MD5HASH();
            DB db = new DB();
            String md5Password = md5.calculateMD5(password);
            String status = db.register(name, email, md5Password);
            out.println(status);
            //out.println("Registered Successfully ");
            //out.println(name + " : " + email + " : " + password);
        }
        catch(Exception e)
        {
            out.println(e);
        }
        finally
        {
            out.close();
        }   
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
