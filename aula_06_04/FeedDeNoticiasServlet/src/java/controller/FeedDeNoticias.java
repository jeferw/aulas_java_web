package controller;

import dao.ComentarioDAO;
import dao.PublicacaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Publicacao;

@WebServlet(name = "FeedDeNoticias", urlPatterns =
{
    "/page"
})
public class FeedDeNoticias extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = response.getWriter())
        {
            saida.println("<html>");
            saida.println("<head>");
            saida.println("  <title>Feed</title> ");
            saida.println("  <link rel=\"stylesheet\" type=\"text/css\" href=\"/feed/index.css\">\n");
            saida.println("</head>");
            saida.println("<body>");
            saida.println("<main>");

            saida.println(" <form method=\"POST\" action=\"/feed/publicacao\" > "
                    + "          <div> "
                    + "              <label for=\"publicacao\">O que vc está pensando?</label> "
                    + "              <textarea id=\"publicacao\" name=\"publicacao\"  rows=\"5\"></textarea> "
                    + "          </div> "
                    + "          <div class=\"btn-content\"> "
                    + "             <input type=\"submit\" value=\"Publicar\" class=\"btn\"> "
                    + "          </div> "
                    + "       </form>");

            try
            {
                List<Publicacao> publicacoes = new PublicacaoDAO().buscarTodosPublicacoes();

                saida.println("<div class=\"publicacoes\">");
                for (Publicacao publicacao : publicacoes)
                {
                    saida.println("<div class=\"card\">");
                    saida.println("  <div class=\"card-body\">");
                    saida.println("    <p>" + publicacao.getPublicacao() + "</p>");
                    saida.println("  </div>");
                    saida.println("  <div class=\"card-footer\">");
                    saida.println("    <span>" + new SimpleDateFormat("dd/MM/yyyy").format(publicacao.getDataTime()) + "</span>");
                    saida.println("    <span>  -  </span>");
                    saida.println("    <span>" + new SimpleDateFormat("HH:mm:ss").format(publicacao.getDataTime()) + "</span>");
                    saida.println("  </div>");

                    List<Comentario> comentarios = new ComentarioDAO().buscarComentariosPublicacao(publicacao);

                    saida.println("  <div class=\"card-comentarios\">");
                    for (Comentario comentario : comentarios)
                    {
                        saida.println("  <div class=\"card\">");
                        saida.println("    <div class=\"card-body\">");
                        saida.println("      <p>" + comentario.getComentario() + "</p>");
                        saida.println("    </div>");
                        saida.println("    <div class=\"card-footer\">");
                        saida.println("      <span>" + new SimpleDateFormat("dd/MM/yyyy").format(comentario.getDataTime()) + "</span>");
                        saida.println("      <span>  -  </span>");
                        saida.println("      <span>" + new SimpleDateFormat("HH:mm:ss").format(comentario.getDataTime()) + "</span>");
                        saida.println("    </div>");
                        saida.println("  </div>");
                    }
                    saida.println("  </div>");

                    saida.println("  <form class=\"form-comentario\" method=\"POST\" action=\"/feed/comentario\"> "
                            + "          <div> "
                            + "              <textarea id=\"comentario\" name=\"comentario\"  rows=\"3\"></textarea> "
                            + "          </div> "
                            + "          <input name=\"idpublicacao\" type=\"hidden\" value=" + publicacao.getIdPublicacao() + "> "
                            + "          <div class=\"btn-content\"> "
                            + "             <input type=\"submit\" value=\"Comentar\" class=\"btn\"> "
                            + "          </div> "
                            + "      </form>");
                    saida.println("</div>");
                }
                saida.println("</div>");
            } catch (SQLException e)
            {
                saida.println("<p>Falha ao buscar as publicacoes!</p>");
                saida.println("<p>Detalhes: " + e.getMessage() + "</p>");
            }

            saida.println("</main>");
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
