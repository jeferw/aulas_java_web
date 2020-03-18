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
    protected void doGet(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        // iniciamos uma variável de controle
        // vamos utilizá-la para checar se algum produto foi encontrado
        boolean encontrouProduto = false;

        String produtoPesquisa = "";
        // validar se o parâmetro pesquisa existe na requisição
        if (requisicao.getParameter("pesquisa") != null)
        {
            // "pegar" o parâmetro enviado pelo formulário que está no index.html
            produtoPesquisa = requisicao.getParameter("pesquisa");
        }

        // cria e popula a lista de produtos
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

            String produtoAtual;

            // percorer todos os produtos da lista
            for (int i = 0; i < produtos.size(); i++)
            {
                // armazena o produto atual em uma variável 
                produtoAtual = produtos.get(i);

                // verifica se o "filtro" está preenchido
                if (!produtoPesquisa.equals(""))
                {
                    // se o "filtro" está preenchido
                    // verificar se o valor informado é igual ao produto atual
                    if (produtoPesquisa.equals(produtoAtual))
                    {
                        // se o "filtro" informado for igual ao produto
                        // adiciona o produto a lista HTML
                        saida.println("<li>" + produtoAtual + "</li>");

                        // altera o valor da variável de controle
                        // informando que um produto foi encontrado
                        encontrouProduto = true;
                    }
                } else
                {
                    // adiciona o produto a lista HTML
                    saida.println("<li>" + produtoAtual + "</li>");

                    // altera o valor da variável de controle
                    // informando que um produto foi encontrado
                    encontrouProduto = true;
                }
            }

            // varificamos se algum produto foi encontrado
            if (!encontrouProduto)
            {
                // se nehum produto foi encontrado, informamos ao usuário
                saida.println("<li>Nenhum Produto encontrado!</li>");
            }
            saida.println("</ul>");
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
