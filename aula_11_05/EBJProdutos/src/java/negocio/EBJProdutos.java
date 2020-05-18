package negocio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import model.ModelProdutos;
import org.json.JSONArray;
import org.json.JSONObject;

@Stateless
public class EBJProdutos
{

    public String getJson(HttpServletRequest request)
    {
        String json
                = //<editor-fold defaultstate="collapsed" desc="JSON">
                "{ "
                + "  \"produtos\": [ "
                + "    { "
                + "      \"id\": 1, "
                + "      \"nome\": \"Hambúrguer\", "
                + "      \"descricao\": \"Pão, bife de hambúrguer 90g, salada e batata.\", "
                + "      \"preco\": 8.5 "
                + "    }, "
                + "    { "
                + "      \"id\": 2, "
                + "      \"nome\": \"X-Búrguer\", "
                + "      \"descricao\": \"Pão, bife de hambúrguer 90g, 1 fatia de queijo, salada e batata.\", "
                + "      \"preco\": 10.5 "
                + "    }, "
                + "    { "
                + "      \"id\": 3, "
                + "      \"nome\": \"X-Bacon\", "
                + "      \"descricao\": \"Pão, bife de hambúrguer 90g, 1 fatia de queijo, 2 fatia de bacon, salada e batata.\", "
                + "      \"preco\": 12.5 "
                + "    }, "
                + "    { "
                + "      \"id\": 4, "
                + "      \"nome\": \"X-Tudo\", "
                + "      \"descricao\": \"Pão, 2 bifes de hambúrguer 90g, 2 fatias de queijo, 4 fatias de bacon, salada e batata.\", "
                + "      \"preco\": 14.5 "
                + "    }, "
                + "    { "
                + "      \"id\": 5, "
                + "      \"nome\": \"Coca cola 350ml\", "
                + "      \"descricao\": \"\", "
                + "      \"preco\": 5.5 "
                + "    }, "
                + "    { "
                + "      \"id\": 6, "
                + "      \"nome\": \"Coca cola 600ml\", "
                + "      \"descricao\": \"\", "
                + "      \"preco\": 7.5 "
                + "    } "
                + "  ] "
                + "}";
        //</editor-fold>

        JSONObject jsonObj = new JSONObject(json);

        JSONArray arrayProdutos = jsonObj.getJSONArray("produtos");

        List<ModelProdutos> listProdutos = new ArrayList<>();

        ModelProdutos produto;
        JSONObject jsonProduto;

        for (int i = 0; i < arrayProdutos.length(); i++)
        {
            jsonProduto = arrayProdutos.getJSONObject(i);

            produto = new ModelProdutos();

            produto.setId(jsonProduto.getInt("id"));
            produto.setNome(jsonProduto.getString("nome"));
            produto.setDescricao(jsonProduto.getString("descricao"));
            produto.setPreco(jsonProduto.getDouble("preco"));

            listProdutos.add(produto);
        }

        JSONObject retorno = new JSONObject();
        //retorno.put("produtos", listProdutos);

        JSONArray arrayProdutosRetorno = new JSONArray();

        for (ModelProdutos produtoAtual : listProdutos)
        {
            jsonProduto = new JSONObject();

            jsonProduto.put("id", produtoAtual.getId());
            jsonProduto.put("nome", produtoAtual.getNome());
            jsonProduto.put("descricao", produtoAtual.getDescricao());
            jsonProduto.put("preco", produtoAtual.getPreco());

            arrayProdutosRetorno.put(jsonProduto);
        }

        retorno.put("produtos", arrayProdutosRetorno);

        return retorno.toString();
    }
}
