package parametros;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servletget")
public class ServletGet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        //Desta forma recuperamos um parametro que foi enviado pelo navegador na url
        String nome = requisicao.getParameter("nome");
        String idade = requisicao.getParameter("idade");

        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            saida.println("<h1>Olá, " + nome + "!</h1>");
            saida.println("<h1>Sua idade é " + idade + "!</h1>");
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
