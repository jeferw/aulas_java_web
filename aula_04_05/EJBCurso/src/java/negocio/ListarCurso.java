package negocio;

import dao.CursoDAO;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import model.MCurso;

@Stateless
public class ListarCurso
{

    public String listarCurso(HttpServletRequest request) throws SQLException
    {
        int id = 0;

        if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
        {
            id = Integer.parseInt(request.getParameter("id"));
        } else
        {
            StringBuilder retorno = new StringBuilder();

            retorno.append("<html>");
            retorno.append("   <head> ");
            retorno.append("      <title>Erro</title> ");
            retorno.append("      <meta charset=\"UTF-8\"> ");
            retorno.append("      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
            retorno.append("   </head> ");
            retorno.append("  <body>");
            retorno.append("    <p><strong>Id não informado!</strong></p>");
            retorno.append("    <a href=\"/crud/curso\">Voltar</a>");
            retorno.append("  </body>");
            retorno.append("</html>");

            return retorno.toString();
        }

        MCurso curso = new CursoDAO().buscarCurso(id);

        StringBuilder retorno = new StringBuilder();

        retorno.append("<html>");
        retorno.append("   <head> ");
        retorno.append("      <title>Listar Curso</title> ");
        retorno.append("      <meta charset=\"UTF-8\"> ");
        retorno.append("      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
        retorno.append("   </head> ");
        retorno.append("<body>");

        retorno.append("<form method=\"POST\" action=\"/crud/curso\">");
        retorno.append("   <label for=\"curso\">Nome do Curso:</label><br>");
        retorno.append("   <input value=\"").append(curso.getCurso()).append("\" id=\"curso\" name=\"curso\" type=\"text\" maxlength=\"60\" required><br><br>");

        retorno.append("   <label for=\"turno\" >Turno:</label><br>");
        retorno.append("   <select id=\"turno\" name=\"turno\">");
        retorno.append("      <option value=\"M\" ").append((curso.getTurno().equals("M") ? "selected" : "")).append(">Manhã</option>");
        retorno.append("      <option value=\"T\" ").append((curso.getTurno().equals("T") ? "selected" : "")).append(">Tarde</option>");
        retorno.append("      <option value=\"N\" ").append((curso.getTurno().equals("N") ? "selected" : "")).append(">Noite</option>");
        retorno.append("   </select><br><br>");

        retorno.append("   <label for=\"qtdeEstudantes\">Quantidade de Estudantes:</label><br>");
        retorno.append("   <input value=\"").append(curso.getQtdeEstudantes()).append("\" id=\"qtdeEstudantes\" name=\"qtdeEstudantes\" type=\"number\"><br><br>");

        retorno.append("   <label for=\"data\">Data de Cadastro:</label><br>");
        retorno.append("   <input value=\"").append(curso.getData()).append("\" id=\"data\" name=\"data\" type=\"date\"><br><br>");

        retorno.append("  <input value=\"").append(curso.getId()).append("\" name=\"id\" type=\"hidden\" >");

        retorno.append("  <input type=\"hidden\" name=\"projeto\" value=\"EJBCurso\"> ");
        retorno.append("  <input type=\"hidden\" name=\"classe\" value=\"Editar\"> ");
        retorno.append("  <input type=\"hidden\" name=\"metodo\" value=\"editarCurso\">");

        retorno.append("  <input type=\"submit\" value=\"Editar Curso\">");

        retorno.append("</form><br>");

        retorno.append(" <a href=\"/crud/curso\">Adicionar Curso</a>");
        retorno.append("</body>");
        retorno.append("</html>");

        return retorno.toString();
    }
}
