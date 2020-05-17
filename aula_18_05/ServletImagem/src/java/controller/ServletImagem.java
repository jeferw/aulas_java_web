package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/retornaimagem")
public class ServletImagem extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        response.setContentType("image/jpg");

        try
        {
            String caminhoImg;

            // recupera os parametros enviados no src da imagem
            String cProduto = request.getParameter("cproduto");

            if (cProduto == null || cProduto.equals(""))
            {
                // alterar caminho para a pasta desejada
                caminhoImg = "C:\\imagens\\imagem_nao_encontrada\\nao_encontrada.jpg";
            } else
            {
                caminhoImg = "C:\\imagens\\" + cProduto + ".jpg";
            }

            File arquivo = new File(caminhoImg);

            if (!arquivo.exists())
            {
                arquivo = new File("C:\\imagens\\imagem_nao_encontrada\\nao_encontrada.jpg");
            }

            inputStream = new FileInputStream(arquivo);

            outputStream = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(ServletImagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ServletImagem.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                } catch (IOException ex)
                {
                }
            }

            if (outputStream != null)
            {
                try
                {
                    outputStream.close();
                } catch (IOException ex)
                {
                }
            }
        }
    }
}
