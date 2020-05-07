package ejbcalculadora;

import ejbconnection.EJBConnection;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class Calculadora
{

    public String pagina(HttpServletRequest dadosRequest)
    {
        StringBuilder retorno = new StringBuilder();

        retorno.append("<html>")
                .append("  <head> "
                        + "     <title>Calculadora</title> "
                        + "     <link rel=\"stylesheet\" type=\"text/css\" href=\"/calculadora/css/css.css\">"
                        + "</head>")
                .append("<body>");

        retorno.append("<form method=\"POST\" action=\"/calculadora/calcular\"> "
                + "            <input type=\"hidden\" name=\"projeto\" value=\"EBJCalculadora\"> "
                + "            <input type=\"hidden\" name=\"classe\" value=\"Calculadora\"> "
                + "            <input type=\"hidden\" name=\"metodo\" value=\"calcular\"> "
                + " "
                + "            <label>Número 1</label> "
                + "            <input type=\"number\" name=\"mumero1\"> "
                + " "
                + "            <label>Número 2</label> "
                + "            <input type=\"number\" name=\"mumero2\"> "
                + " "
                + "            <label>Operação</label> "
                + "            <select name=\"operacao\"> "
                + "                <option value=\"somar\">Somar</option> "
                + "                <option value=\"subtrair\">Subtrair</option> "
                + "                <option value=\"multiplicar\">Multiplicar</option> "
                + "                <option value=\"dividir\">Dividir</option> "
                + "            </select> "
                + " "
                + "            <input type=\"submit\" value=\"Calcular\"> "
                + "        </form>");

        retorno.append("</body>")
                .append("</html>");

        return retorno.toString();
    }

    public String calcular(HttpServletRequest dadosRequest) throws Exception
    {
        double num1, num2, resultado = 0;

        String operacao;

        StringBuilder retorno = new StringBuilder();

        retorno.append("<html>")
                .append("  <head> "
                        + "     <title>Resultado</title> "
                        + "     <link rel=\"stylesheet\" type=\"text/css\" href=\"/calculadora/css/css.css\">"
                        + "</head>")
                .append("<body>");

        if (dadosRequest.getParameter("mumero1") != null && !dadosRequest.getParameter("mumero1").equals(""))
        {
            num1 = Double.parseDouble(dadosRequest.getParameter("mumero1"));
        } else
        {
            retorno.append("<p><strong>Número 1 não informado!</strong></p>");
            retorno.append("<a href=\"/calculadora/calcular\">Voltar</a>");
            retorno.append("</body>")
                    .append("</html>");

            return retorno.toString();
        }

        if (dadosRequest.getParameter("mumero2") != null && !dadosRequest.getParameter("mumero2").equals(""))
        {
            num2 = Double.parseDouble(dadosRequest.getParameter("mumero2"));
        } else
        {
            retorno.append("<p><strong>Número 2 não informado!</strong></p>");
            retorno.append("<a href=\"/calculadora/calcular\">Voltar</a>");
            retorno.append("</body>")
                    .append("</html>");

            return retorno.toString();
        }

        if (dadosRequest.getParameter("operacao") != null && !dadosRequest.getParameter("operacao").equals(""))
        {
            operacao = dadosRequest.getParameter("operacao");
        } else
        {
            retorno.append("<p><strong>Operação não informada!</strong></p>");
            retorno.append("<a href=\"/calculadora/calcular\">Voltar</a>");
            retorno.append("</body>")
                    .append("</html>");

            return retorno.toString();
        }

        retorno.append("<p>")
                .append("O resultado da ");

        switch (operacao)
        {
            case "somar":
                retorno.append("adição ");
                resultado = (double) EJBConnection.execMethod("EBJCalcular/Calcular", "somar", num1, num2);
                break;

            case "subtrair":
                retorno.append("subtração ");
                resultado = (double) EJBConnection.execMethod("EBJCalcular/Calcular", "subtrair", num1, num2);
                break;

            case "multiplicar":
                retorno.append("multiplicação ");
                resultado = (double) EJBConnection.execMethod("EBJCalcular/Calcular", "multiplicar", num1, num2);
                break;

            case "dividir":
                retorno.append("divisão ");
                resultado = (double) EJBConnection.execMethod("EBJCalcular/Calcular", "dividir", num1, num2);
                break;
        }

        retorno.append("entre ")
                .append(num1)
                .append(" e ")
                .append(num2)
                .append(" é ")
                .append(resultado)
                .append("!");

        retorno.append("</p>");

        retorno.append("<a href=\"/calculadora/calcular\">Voltar</a>");

        retorno.append("</body>")
                .append("</html>");

        return retorno.toString();
    }
}
