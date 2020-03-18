package atividade;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pedidoproduto")
public class PedidoProdutoServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        String cliente = "", cnpj = "", endereco = "", produto = "";
        double qtde = 0.0, valorUnitario = 0.0, valorTotal;

        if (requisicao.getParameter("cliente") != null)
        {
            cliente = requisicao.getParameter("cliente");
        }
        if (requisicao.getParameter("cnpj") != null)
        {
            cnpj = requisicao.getParameter("cnpj");
        }
        if (requisicao.getParameter("endereco") != null)
        {
            endereco = requisicao.getParameter("endereco");
        }
        if (requisicao.getParameter("produto") != null)
        {
            produto = requisicao.getParameter("produto");
        }
        if (requisicao.getParameter("preco") != null && !requisicao.getParameter("preco").equals(""))
        {
            valorUnitario = Double.parseDouble(requisicao.getParameter("preco"));
        }
        if (requisicao.getParameter("qtde") != null && !requisicao.getParameter("qtde").equals(""))
        {
            qtde = Double.parseDouble(requisicao.getParameter("qtde"));
        }

        valorTotal = valorUnitario * qtde;

        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            saida.println("<p><strong>Nome do Cliente:</strong> " + cliente + "</p>");
            saida.println("<p><strong>CNPJ:</strong> " + cnpj + "</p>");
            saida.println("<p><strong>Endereço:</strong> " + endereco + "</p>");
            saida.println("<p><strong>Nome do Produto:</strong> " + produto + "</p>");
            saida.println("<p><strong>Quantidade do Produto:</strong> " + qtde + "</p>");
            saida.println("<p><strong>Valor do Produto:</strong> " + valorUnitario + "</p>");
            saida.println("<p><strong>Total do Produto:</strong> " + valorTotal + "</p>");
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
