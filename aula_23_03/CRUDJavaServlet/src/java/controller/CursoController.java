package controller;

import dao.CursoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MCurso;

@WebServlet(name = "CursoController", urlPatterns =
{
    "/curso"
})
public class CursoController extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter saida = response.getWriter())
        {
            saida.println("<html>");
            saida.println("<body>");

            if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
            {
                try
                {
                    MCurso curso = new CursoDAO().buscarCurso(Integer.parseInt(request.getParameter("id")));

                    saida.println("<form method=\"POST\" action=\"/crud/curso\">");
                    saida.println("  <label for=\"curso\">Nome do Curso:</label><br>");
                    saida.println("  <input value=\"" + curso.getCurso() + "\" id=\"curso\" name=\"curso\" type=\"text\" maxlength=\"60\" required ><br><br>");
                    saida.println("  <label for=\"turno\">Turno:</label><br>");
                    saida.println("  <select id=\"turno\" name=\"turno\">");
                    saida.println("    <option value=\"M\" " + (curso.getTurno().equals("M") ? "selected" : "") + ">Manhã</option>");
                    saida.println("    <option value=\"T\" " + (curso.getTurno().equals("T") ? "selected" : "") + ">Tarde</option>");
                    saida.println("    <option value=\"N\" " + (curso.getTurno().equals("N") ? "selected" : "") + ">Noite</option>");
                    saida.println("  </select><br><br>");
                    saida.println("  <label for=\"qtdeEstudantes\">Quantidade de Estudantes:</label><br>");
                    saida.println("  <input value=\"" + curso.getQtdeEstudantes() + "\" id=\"qtdeEstudantes\" name=\"qtdeEstudantes\" type=\"number\" ><br><br>");
                    saida.println("  <label for=\"data\">Data de Cadastro:</label><br>");
                    saida.println("  <input value=\"" + curso.getData() + "\" id=\"data\" name=\"data\" type=\"date\" required><br><br>");
                    saida.println("  <input value=\"" + curso.getId() + "\" name=\"id\" type=\"hidden\" >");
                    saida.println("  <input name=\"acao\" value=\"editar\" type=\"hidden\"> ");
                    saida.println("  <input type=\"submit\" value=\"Editar Curso\"> ");
                    saida.println("</form><br> ");
                    saida.println("<a href=\"/crud/curso\">Listar Cursos</a>");

                } catch (SQLException e)
                {
                    saida.println("<p>Falha ao buscar o curso! Detalhes: " + e.getMessage() + "</p>");
                }
            } else
            {
                try
                {
                    List<MCurso> cursos = new CursoDAO().buscarTodosCursos();

                    saida.println("<table border=\"1\">");
                    saida.println("  <tr>");
                    saida.println("    <th>Id</th>");
                    saida.println("    <th>Curso</th>");
                    saida.println("    <th>Turno</th>");
                    saida.println("    <th>Qtde Alunos</th>");
                    saida.println("    <th>Data Cadastro</th>");
                    saida.println("    <th>Editar Curso</th>");
                    saida.println("    <th>Deletar Curso</th>");
                    saida.println("  </tr>");

                    for (MCurso curso : cursos)
                    {
                        saida.println("<tr>");
                        saida.println("  <td>" + curso.getId() + "</td>");
                        saida.println("  <td>" + curso.getCurso() + "</td>");
                        saida.println("  <td>" + curso.getTurno() + "</td>");
                        saida.println("  <td>" + curso.getQtdeEstudantes() + "</td>");
                        saida.println("  <td>" + new SimpleDateFormat("dd/MM/yyyy").format(curso.getData()) + "</td>");
                        saida.println("  <td><a href=\"/crud/curso?id=" + curso.getId() + "\">Editar</a></td>");
                        saida.println("  <td>"
                                + "         <form method=\"POST\" action=\"/crud/curso\"> "
                                + "            <input name=\"id\" type=\"hidden\" value=" + curso.getId() + "> "
                                + "            <input name=\"acao\" type=\"hidden\" value=\"excluir\"> "
                                + "            <input type=\"submit\" value=\"Deletar\"> "
                                + "         </form>"
                                + "       </td>");
                        saida.println("</tr>");
                    }

                    saida.println("</table>");
                    saida.println("<a href=\"/crud/curso.html\">Adicionar Cursos</a>");

                } catch (SQLException e)
                {
                    saida.println("<p>Falha ao buscar os cursos! Detalhes: " + e.getMessage() + "</p>");
                }
            }
            saida.println("</body>");
            saida.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        String curso = "", turno = "", dataStr = "";
        int id = 0, qtdeEstudantes = 0;

        String acao = request.getParameter("acao");

        if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
        {
            id = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("curso") != null)
        {
            curso = request.getParameter("curso");
        }
        if (request.getParameter("turno") != null)
        {
            turno = request.getParameter("turno");
        }
        if (request.getParameter("qtdeEstudantes") != null && !request.getParameter("qtdeEstudantes").equals(""))
        {
            qtdeEstudantes = Integer.parseInt(request.getParameter("qtdeEstudantes"));
        }
        if (request.getParameter("data") != null)
        {
            dataStr = request.getParameter("data");
        }
        Date data;
        try
        {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(dataStr);
        } catch (ParseException ex)
        {
            data = new Date();
        }

        MCurso mCurso = new MCurso();
        mCurso.setId(id);
        mCurso.setCurso(curso);
        mCurso.setTurno(turno);
        mCurso.setQtdeEstudantes(qtdeEstudantes);
        mCurso.setData(data);

        try
        {
            switch (acao)
            {
                case "inserir":
                    new CursoDAO().salvar(mCurso);
                    break;
                case "editar":
                    new CursoDAO().editar(mCurso);
                    break;
                case "excluir":
                    new CursoDAO().excluir(mCurso);
                    break;
            }
            // redireciona para o recurso de criar a tabela
            response.sendRedirect("/crud/curso");
        } catch (SQLException e)
        {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter saida = response.getWriter())
            {
                request.getRequestDispatcher("/erro.html").include(request, response);

                saida.println("<p>Ocorreu um erro!</p>");
                saida.println("<p>Detalhes: " + e.getMessage() + "</p>");
            }
        }
    }
}
