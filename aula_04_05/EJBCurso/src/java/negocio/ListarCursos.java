package negocio;

import dao.CursoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import model.MCurso;

@Stateless
public class ListarCursos
{

    public String listarCursos(HttpServletRequest request) throws SQLException
    {
        List<MCurso> cursos = new CursoDAO().buscarTodosCursos();

        StringBuilder retorno = new StringBuilder();

        retorno.append("<html>");
        retorno.append("   <head> ");
        retorno.append("      <title>Listar Curso</title> ");
        retorno.append("      <meta charset=\"UTF-8\"> ");
        retorno.append("      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
        retorno.append("   </head> ");
        retorno.append("<body>");

        retorno.append(" <table border=\"1\">");
        retorno.append("  <tr>");
        retorno.append("     <th>Id</th>");
        retorno.append("     <th>Curso</th>");
        retorno.append("     <th>Turno</th>");
        retorno.append("     <th>Qtde Alunos</th>");
        retorno.append("     <th>Data</th>");
        retorno.append("     <th>Editar Curso</th>");
        retorno.append("     <th>Excluir Curso</th>");
        retorno.append("  </tr>");

        for (MCurso curso : cursos)
        {
            retorno.append(" <tr>");
            retorno.append("   <td>").append(curso.getId()).append("</td>");
            retorno.append("   <td>").append(curso.getCurso()).append("</td>");
            retorno.append("   <td>").append(curso.getTurno()).append("</td>");
            retorno.append("   <td>").append(curso.getQtdeEstudantes()).append("</td>");
            retorno.append("   <td>").append(new SimpleDateFormat("dd/MM/yyyy").format(curso.getData())).append("</td>");
            retorno.append("   <td>");
            retorno.append("      <form method=\"POST\" action=\"/crud/curso\">");

            retorno.append("         <input name=\"id\" type=\"hidden\" value=").append(curso.getId()).append("> ");

            retorno.append("         <input type=\"hidden\" name=\"projeto\" value=\"EJBCurso\"> ");
            retorno.append("         <input type=\"hidden\" name=\"classe\" value=\"ListarCurso\"> ");
            retorno.append("         <input type=\"hidden\" name=\"metodo\" value=\"listarCurso\">");

            retorno.append("         <input type=\"submit\" value=\"Editar\"> ");
            retorno.append("      </form>");
            retorno.append("   </td>");
            retorno.append("   <td>");
            retorno.append("      <form method=\"POST\" action=\"/crud/curso\">");

            retorno.append("         <input name=\"id\" type=\"hidden\" value=").append(curso.getId()).append("> ");

            retorno.append("         <input type=\"hidden\" name=\"projeto\" value=\"EJBCurso\"> ");
            retorno.append("         <input type=\"hidden\" name=\"classe\" value=\"Excluir\"> ");
            retorno.append("         <input type=\"hidden\" name=\"metodo\" value=\"excluirCurso\">");

            retorno.append("         <input type=\"submit\" value=\"Excluir\"> ");
            retorno.append("      </form>");
            retorno.append("   </td>");
            retorno.append(" </tr>");
        }

        retorno.append(" </table>");

        retorno.append(" <a href=\"/crud/curso\">Adicionar Curso</a>");

        retorno.append("</body>");
        retorno.append("</html>");

        return retorno.toString();
    }
}
