package invocandoejb;

import ejbconnection.EJBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/invocar")
public class InvocandoEJB extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Olá Mundo EJB</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>EJB diz: " + EJBConnection.execMethod("OlaMundoEJB/MeuPrimeiroEJB", "digaMeuNome", "Jeferson") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex)
        {
            Logger.getLogger(InvocandoEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
