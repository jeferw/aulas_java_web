package atividade;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/produtos")
public class ProdutoServlet extends HttpServlet
{

    @Override
    protected void service(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        List<String> produtos = new ArrayList<>();
        produtos.add("Maçã");
        produtos.add("Banana");
        produtos.add("Bolacha");

        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            saida.println("<h1>Lista de Produtos</h1>");
            saida.println("<ul>");
            for (String produto : produtos)
            {
                saida.println("<li>" + produto + "</li>");
            }
            saida.println("</ul>");
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
