package negocio;

import dao.CursoDAO;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import model.MCurso;

@Stateless
public class Excluir
{

    public String excluirCurso(HttpServletRequest request) throws SQLException
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

        MCurso mCurso = new MCurso();
        mCurso.setId(id);

        new CursoDAO().excluir(mCurso);

        return new ListarCursos().listarCursos(request);
    }
}
