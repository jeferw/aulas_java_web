package clientesocket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocket
{
    public static void main(String[] args)
    {
        try
        {
            Socket cliente = new Socket("127.0.0.1", 7078);
            System.out.println("O cliente se conectou ao servidor!");

            PrintStream saida = new PrintStream(cliente.getOutputStream());

            Scanner teclado = new Scanner(System.in);

            while (teclado.hasNextLine())
            {
                saida.println(teclado.nextLine());
            }

            saida.close();
            teclado.close();
            cliente.close();
        } catch (IOException ex)
        {
            System.out.println("Erro ao se conectar ao servidor: " 
                    + ex.getMessage());
        }
    }
}
