package atividade;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/tabuada")
public class Tabuada extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");

            if (requisicao.getParameter("numero") == null 
                    || requisicao.getParameter("numero").equals(""))
            {
                saida.println("<h3><strong>Nenhum Número Informado!</strong></h3>");
            } else
            {
                int numero = Integer.parseInt(requisicao.getParameter("numero"));

                saida.println("<table border=\"1\">");
                for (int i = 1; i <= 10; i++)
                {
                    saida.println("<tr>");

                    saida.println("<td>" + numero + "</td>");
                    saida.println("<td>x</td>");
                    saida.println("<td>" + i + "</td>");
                    saida.println("<td>=</td>");
                    saida.println("<td>" + (numero * i) + "</td>");

                    saida.println("</tr>");
                }
                saida.println("</table>");
            }
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
