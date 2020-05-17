package negocio;

import dao.CursoDAO;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import model.MCurso;

@Stateless
public class Editar
{

    public String editarCurso(HttpServletRequest request) throws SQLException
    {
        String curso = "";
        String turno = "";
        int qtdeEstudantes = 0;
        String dataStr = "";
        int id = 0;

        boolean returnErro = false;
        String campoErro = "";

        if (request.getParameter("id") != null && !request.getParameter("id").equals(""))
        {
            id = Integer.parseInt(request.getParameter("id"));
        } else
        {
            returnErro = true;
            campoErro = "Id";
        }

        if (request.getParameter("curso") != null && !request.getParameter("curso").equals(""))
        {
            curso = request.getParameter("curso");
        } else
        {
            returnErro = true;
            campoErro = "Curso";
        }

        if (request.getParameter("turno") != null && !request.getParameter("turno").equals(""))
        {
            turno = request.getParameter("turno");
        } else
        {
            returnErro = true;
            campoErro = "Turno";
        }

        if (request.getParameter("qtdeEstudantes") != null && !request.getParameter("qtdeEstudantes").equals(""))
        {
            qtdeEstudantes = Integer.parseInt(request.getParameter("qtdeEstudantes"));
        } else
        {
            returnErro = true;
            campoErro = "Quantidade Estudantes";
        }

        if (request.getParameter("data") != null && !request.getParameter("data").equals(""))
        {
            dataStr = request.getParameter("data");
        } else
        {
            returnErro = true;
            campoErro = "Data";
        }

        if (returnErro)
        {
            StringBuilder retorno = new StringBuilder();

            retorno.append("<html>");
            retorno.append("   <head> ");
            retorno.append("      <title>Erro</title> ");
            retorno.append("      <meta charset=\"UTF-8\"> ");
            retorno.append("      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ");
            retorno.append("   </head> ");
            retorno.append("  <body>");
            retorno.append("    <p><strong>").append(campoErro).append(" não informado!</strong></p>");
            retorno.append("    <a href=\"/crud/curso\">Voltar</a>");
            retorno.append("  </body>");
            retorno.append("</html>");

            return retorno.toString();
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

        new CursoDAO().editar(mCurso);

        return new ListarCursos().listarCursos(request);
    }
}
