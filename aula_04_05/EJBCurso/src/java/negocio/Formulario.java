package negocio;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class Formulario
{

    public String getFormulario(HttpServletRequest request)
    {
        StringBuilder retorno = new StringBuilder();
        retorno.append("      <html> ")
                .append("     <head> ")
                .append("        <title>Adicionar Curso</title> ")
                .append("        <meta charset=\"UTF-8\"> ")
                .append("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> ")
                .append("     </head> ")
                .append("     <body> ")
                .append("        <form method=\"POST\" action=\"/crud/curso\"> ")
                .append("            <label for=\"curso\">Nome do Curso:</label><br> ")
                .append("            <input id=\"curso\" name=\"curso\" type=\"text\" maxlength=\"60\" required><br><br> ")
                .append("            <label for=\"turno\" >Turno:</label><br> ")
                .append("            <select id=\"turno\" name=\"turno\"> ")
                .append("                <option value=\"M\">Manhã</option> ")
                .append("                <option value=\"T\">Tarde</option> ")
                .append("                <option value=\"N\">Noite</option> ")
                .append("            </select><br><br> ")
                .append("            <label for=\"qtdeEstudantes\">Quantidade de Estudantes:</label><br> ")
                .append("            <input id=\"qtdeEstudantes\" name=\"qtdeEstudantes\" type=\"number\"><br><br> ")
                .append("            <label for=\"data\">Data de Cadastro:</label><br> ")
                .append("            <input id=\"data\" name=\"data\" type=\"date\"><br><br> ")
                //
                .append("            <input type=\"hidden\" name=\"projeto\" value=\"EJBCurso\"> ")
                .append("            <input type=\"hidden\" name=\"classe\" value=\"Inserir\"> ")
                .append("            <input type=\"hidden\" name=\"metodo\" value=\"inserirCurso\">")
                //
                .append("            <input type=\"submit\" value=\"Cadastrar Curso\"> ")
                .append("        </form><br> ")
                .append("        <form id=\"form\" method=\"POST\" action=\"/crud/curso\"> ")
                //
                .append("            <input type=\"hidden\" name=\"projeto\" value=\"EJBCurso\"> ")
                .append("            <input type=\"hidden\" name=\"classe\" value=\"ListarCursos\"> ")
                .append("            <input type=\"hidden\" name=\"metodo\" value=\"listarCursos\">")
                //
                .append("            <input type=\"submit\" value=\"Listar Cursos\"> ")
                .append("        </form>")
                .append("     </body> ")
                .append("     </html> ");
        return retorno.toString();
    }
}
