package json;

import org.json.JSONObject;

public class Exemplo1
{

    public static void main(String[] args)
    {
        // Criamos um novo JSON
        JSONObject json_cliente = new JSONObject();

        // Adicionamos valores ao JSON
        json_cliente.put("id", 1);
        json_cliente.put("nome", "Jair");
        json_cliente.put("sobrenome", "Silva");
        json_cliente.put("idade", 45);
        json_cliente.put("credito", 420.5);
        json_cliente.put("casado", true);

        // Alteramos o valor da chave credito
        json_cliente.put("credito", 400.5);

        // Acessamos os valores contidos no JSON
        int id = json_cliente.getInt("id");
        String nome = json_cliente.getString("nome");
        double credito = json_cliente.getDouble("credito");
        boolean casado = json_cliente.getBoolean("casado");

        System.out.println("Id: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Crédito: " + credito);
        System.out.println("Casado: " + casado);

        // Convertemos o objeto JSON para uma String JSON
        String json_string = json_cliente.toString();

        System.out.print("JSON String: ");
        System.out.println(json_string);

        // String JSON
        String string_json = "{\"idade\":45,\"credito\":400.5,\"nome\":\"Jair\",\"id\":1,\"casado\":true,\"sobrenome\":\"Silva\"}";

        // Convertemos uma String em um objeto JSON
        JSONObject new_json = new JSONObject(string_json);

        id = new_json.getInt("id");
        nome = new_json.getString("nome");
        credito = new_json.getDouble("credito");
        casado = new_json.getBoolean("casado");

        System.out.println("Id: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Crédito: " + credito);
        System.out.println("Casado: " + casado);
    }
}
