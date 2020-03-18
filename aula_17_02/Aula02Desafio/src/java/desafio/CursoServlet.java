package desafio;

import dbconnection.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/curso")
public class CursoServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        requisicao.setCharacterEncoding("UTF-8");

        String curso = "", turno = "", data = "";
        int qtdeEstudantes = 0;

        if (requisicao.getParameter("curso") != null)
        {
            curso = requisicao.getParameter("curso");
        }
        if (requisicao.getParameter("turno") != null)
        {
            turno = requisicao.getParameter("turno");
        }
        if (requisicao.getParameter("qtdeEstudantes") != null)
        {
            qtdeEstudantes = Integer.parseInt(requisicao.getParameter("qtdeEstudantes"));
        }
        if (requisicao.getParameter("data") != null)
        {
            data = requisicao.getParameter("data");
        }

        String sql = " "
                + " INSERT INTO CURSO (CURSO, TURNO, QTDEALUNOS, DATA) "
                + " VALUES (?, ?, ?, ?);";

        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            try
            {
                Connection con = DatabaseConnection.getInstance().getConnection();

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, curso);
                ps.setString(2, turno);
                ps.setInt(3, qtdeEstudantes);
                ps.setString(4, data);
                ps.execute();

                ps.close();

                saida.println("<p>Registro Inserido</p>");
                saida.println("<a href=\"/desafio/curso\">Listar Cursos</a>");

            } catch (SQLException e)
            {
                saida.println("<p>Falha ao inserir! Detalhes: " + e.getMessage() + "</p>");
            }
            saida.println("</body>");
            saida.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest requisicao,
            HttpServletResponse resposta) throws IOException
    {
        resposta.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = resposta.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");
            try
            {
                Connection con = DatabaseConnection.getInstance().getConnection();

                PreparedStatement ps;
                ResultSet rs;

                String sql = " "
                        + " SELECT CURSO.CURSO, CURSO.TURNO, CURSO.QTDEALUNOS, CURSO.DATA "
                        + " FROM CURSO  ";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                saida.println("<table border=\"1\">");
                saida.println("<tr>");
                saida.println("<th>Curso</th>");
                saida.println("<th>Turno</th>");
                saida.println("<th>Qtde Alunos</th>");
                saida.println("<th>Data Cadastro</th>");
                saida.println("</tr>");

                while (rs.next())
                {
                    saida.println("<tr>");
                    saida.println("<td>" + rs.getString("CURSO") + "</td>");
                    saida.println("<td>" + rs.getString("TURNO") + "</td>");
                    saida.println("<td>" + rs.getInt("QTDEALUNOS") + "</td>");
                    saida.println("<td>" + rs.getDate("DATA") + "</td>");
                    saida.println("</tr>");
                }

                saida.println("</table>");
                
                saida.println("<br><a href=\"/desafio/curso.html\">Cadastrar Curso</a>");

                ps.close();
            } catch (SQLException e)
            {
                saida.println("<p>Falha ao buscar os cursos! Detalhes: " + e.getMessage() + "</p>");
            }
            saida.println("</body>");
            saida.println("</html>");
        }
    }
}
