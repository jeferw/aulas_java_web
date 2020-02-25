package olamundoweb;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/servlet")
public class OlaMundoServlet extends HttpServlet
{

    @Override
    protected void service(HttpServletRequest requisicao, HttpServletResponse resposta) throws IOException
    {
        //Define que a resposta será um texto ou HTML
        resposta.setContentType("text/html");
        //Define que o charset da resposta será do tipo UTF-8
        resposta.setCharacterEncoding("UTF-8");

        //Do Objeto resposta buscamos uma instância da classe PrinterWriter; 
        //Nessa instância podemos escrever o texto que será visualizado no navegador após a execução do Servlet
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            saida.println("<h1>Olá Mundo Web</h1>");
            saida.println("<h2>Meu Primeiro Servlet</h2>");
            saida.println("</body>");
            saida.println("</html>");
        }

    }
}
