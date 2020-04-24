package controller;

import dao.ComentarioDAO;
import dao.PublicacaoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Comentario;
import model.Publicacao;

@WebServlet(name = "ComentarioController", urlPatterns =
{
    "/comentario"
})
public class ComentarioController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String comentario = "";
        int idPublicacao = 0;

        if (request.getParameter("comentario") != null)
        {
            comentario = request.getParameter("comentario");
        } else
        {
            response.sendRedirect("/feed/page");
        }

        if (request.getParameter("idpublicacao") != null)
        {
            idPublicacao = Integer.parseInt(request.getParameter("idpublicacao"));
        } else
        {
            response.sendRedirect("/feed/page");
        }

        Publicacao mPublicacao = new Publicacao();

        mPublicacao.setIdPublicacao(idPublicacao);

        Comentario mComentario = new Comentario();

        mComentario.setComentario(comentario);
        mComentario.setPublicacao(mPublicacao);
        mComentario.setDataTime(new Date());

        try
        {
            new ComentarioDAO().salvar(mComentario);

            response.sendRedirect("/feed/page");
        } catch (SQLException e)
        {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter saida = response.getWriter())
            {

                saida.println("<p>Ocorreu um erro!</p>");
                saida.println("<p>Detalhes: " + e.getMessage() + "</p>");
            }
        }
    }
}
