package controller;

import dao.PublicacaoDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Publicacao;

@WebServlet(name = "PublicacaoController", urlPatterns =
{
    "/publicacao"
})
@MultipartConfig
public class PublicacaoController extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        String publicacao = "", caminhoImg = "";

        if (request.getParameter("publicacao") != null)
        {
            publicacao = request.getParameter("publicacao");
        } else
        {
            response.sendRedirect("/feed/page");
        }

        Publicacao mPublicacao = new Publicacao();

        mPublicacao.setPublicacao(publicacao);
        mPublicacao.setDataTime(new Date());

        try
        {
            new PublicacaoDAO().salvar(mPublicacao);

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
