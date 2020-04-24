package controller;

import dao.AlunoDAO;
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
import model.MAluno;
import model.MCurso;

@WebServlet(name = "AlunoController", urlPatterns =
{
    "/aluno"
})
public class AlunoController extends HttpServlet
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
                    MAluno aluno = new AlunoDAO().buscarAluno(Integer.parseInt(request.getParameter("id")));

                    saida.println("<form method=\"POST\" action=\"/crud/aluno\"> "
                            + "         <label for=\"aluno\">Nome do Aluno</label><br> "
                            + "         <input value=\"" + aluno.getNome() + "\" id=\"aluno\" name=\"aluno\" type=\"text\" maxlength=\"60\" required ><br><br> "
                            + "         <label for=\"datanascimento\">Data de Nascimento</label><br> "
                            + "         <input value=\"" + aluno.getDataNascimento() + "\" id=\"datanascimento\" name=\"datanascimento\" type=\"date\" required><br><br> "
                            + "         <label for=\"idcurso\">Código Curso:</label><br> "
                            + "         <input value=\"" + aluno.getCurso().getId() + "\" id=\"idcurso\" name=\"idcurso\" type=\"number\" ><br><br> "
                            + "         <input name=\"id\" type=\"hidden\" value=" + aluno.getId() + "> "
                            + "         <input name=\"acao\" value=\"editar\" type=\"hidden\">  "
                            + "         <input type=\"submit\" value=\"Editar Aluno\"> "
                            + "    </form><br> "
                            + "    <a href=\"/crud/aluno\">Listar Alunos</a>  ");

                } catch (SQLException e)
                {
                    saida.println("<p>Falha ao buscar o aluno! Detalhes: " + e.getMessage() + "</p>");
                }
            } else
            {
                try
                {
                    List<MAluno> alunos = new AlunoDAO().buscarTodosAlunos();

                    saida.println("<table border=\"1\">");
                    saida.println("  <tr>");
                    saida.println("    <th>Id</th>");
                    saida.println("    <th>Curso Id</th>");
                    saida.println("    <th>Curso</th>");
                    saida.println("    <th>Aluno</th>");
                    saida.println("    <th>Data Nascimento</th>");
                    saida.println("    <th>Editar Aluno</th>");
                    saida.println("    <th>Deletar Aluno</th>");
                    saida.println("  </tr>");

                    for (MAluno aluno : alunos)
                    {
                        saida.println("<tr>");
                        saida.println("  <td>" + aluno.getId() + "</td>");
                        saida.println("  <td>" + aluno.getCurso().getId() + "</td>");
                        saida.println("  <td>" + aluno.getCurso().getCurso() + "</td>");
                        saida.println("  <td>" + aluno.getNome() + "</td>");
                        saida.println("  <td>" + new SimpleDateFormat("dd/MM/yyyy").format(aluno.getDataNascimento()) + "</td>");
                        saida.println("  <td><a href=\"/crud/aluno?id=" + aluno.getId() + "\">Editar</a></td>");
                        saida.println("  <td>"
                                + "         <form method=\"POST\" action=\"/crud/aluno\"> "
                                + "            <input name=\"id\" type=\"hidden\" value=" + aluno.getId() + "> "
                                + "            <input name=\"acao\" type=\"hidden\" value=\"excluir\"> "
                                + "            <input type=\"submit\" value=\"Deletar\"> "
                                + "         </form>"
                                + "       </td>");
                        saida.println("</tr>");
                    }

                    saida.println("</table>");
                    saida.println("<a href=\"/crud/aluno.html\">Adicionar Alunos</a>");

                } catch (SQLException e)
                {
                    saida.println("<p>Falha ao buscar os alunos! Detalhes: " + e.getMessage() + "</p>");
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

        String aluno = "", dataNascimentoStr = "";
        int id = 0, idCurso = 0;

        String acao = request.getParameter("acao");

        if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
        {
            id = Integer.parseInt(request.getParameter("id"));
        }
        if (request.getParameter("aluno") != null)
        {
            aluno = request.getParameter("aluno");
        }
        if (request.getParameter("idcurso") != null && !request.getParameter("idcurso").equals(""))
        {
            idCurso = Integer.parseInt(request.getParameter("idcurso"));
        }
        if (request.getParameter("datanascimento") != null)
        {
            dataNascimentoStr = request.getParameter("datanascimento");
        }
        Date data;
        try
        {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimentoStr);
        } catch (ParseException ex)
        {
            data = new Date();
        }

        MCurso mCurso = new MCurso();
        mCurso.setId(idCurso);

        MAluno mAluno = new MAluno();
        mAluno.setId(id);
        mAluno.setNome(aluno);
        mAluno.setDataNascimento(data);

        mAluno.setCurso(mCurso);

        try
        {
            switch (acao)
            {
                case "inserir":
                    new AlunoDAO().salvar(mAluno);
                    break;
                case "editar":
                    new AlunoDAO().editar(mAluno);
                    break;
                case "excluir":
                    new AlunoDAO().excluir(mAluno);
                    break;
            }

            response.sendRedirect("/crud/aluno");
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
