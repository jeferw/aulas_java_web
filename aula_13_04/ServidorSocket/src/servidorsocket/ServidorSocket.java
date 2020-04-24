package servidorsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorSocket
{

    public static void main(String[] args)
    {
        try
        {
            ServerSocket servidor = new ServerSocket(7078);
            System.out.println("Porta 7078 aberta!");

            Socket cliente = servidor.accept();
            System.out.println("Nova conexão com o cliente: "
                    + cliente.getInetAddress().getHostAddress()
            ); // imprime o ip do cliente

            Scanner scanner = new Scanner(cliente.getInputStream());

            while (scanner.hasNextLine())
            {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
            cliente.close();
            servidor.close();
        } catch (IOException ex)
        {
            System.out.println("Erro ao iniciar o servidor: "
                    + ex.getMessage()
            );
        }
    }
}
