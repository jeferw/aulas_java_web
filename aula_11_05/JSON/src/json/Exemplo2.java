package json;

import org.json.JSONArray;
import org.json.JSONObject;

public class Exemplo2
{

    public static void main(String[] args)
    {

        // Criando um novo JSONArray
        JSONArray cliantes = new JSONArray();

        // Criando dois JSONObject (Clientes)
        String string_json = "{\"idade\":45,\"credito\":400.5,\"nome\":\"Jair\",\"id\":1,\"casado\":true,\"sobrenome\":\"Silva\"}";
        JSONObject cliente_01 = new JSONObject(string_json);

        JSONObject cliente_02 = new JSONObject();
        cliente_02.put("id", 2);
        cliente_02.put("nome", "João");
        cliente_02.put("sobrenome", "Barbosa");
        cliente_02.put("idade", 36);
        cliente_02.put("credito", 220.5);
        cliente_02.put("casado", false);

        // Adicionando o Cliente 01
        cliantes.put(cliente_01);

        // Adicionando o Cliente 02
        cliantes.put(1, cliente_02);

        // Percorremos o JSONArray recuperando seus itens
        for (int i = 0; i < cliantes.length(); i++)
        {
            System.out.println("Cliente " + (i + 1) + ": " + cliantes.get(i).toString());
        }

        // Criamos um JSONObject vazio
        JSONObject retorno = new JSONObject();
        // Adicionamos o JSONArray ao JSONObject
        retorno.put("clientes", cliantes);

        System.out.println(retorno.toString());
    }
}
