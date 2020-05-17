package controller;

import ejbconnection.EJBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/curso")
public class CursoController extends HttpServlet
{

    private void processarRequest(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String projeto = request.getParameter("projeto");
        String classe = request.getParameter("classe");
        String metodo = request.getParameter("metodo");

        if (projeto == null || projeto.equals(""))
        {
            projeto = "EJBCurso";
            classe = "Formulario";
            metodo = "getFormulario";
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = response.getWriter())
        {

            try
            {
                saida.print(EJBConnection.execMethod(projeto + "/" + classe, metodo, request));
            } catch (Exception ex)
            {
                saida.print("<html>");
                saida.print("<body>");
                saida.print("<p><strong>Ocorreu um erro: " + ex.getMessage() + "</strong></p>");
                saida.print("</body>");
                saida.print("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processarRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        processarRequest(req, resp);
    }
}
