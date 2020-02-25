package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection
{

    // Instancia da classe, atributo privado e estático
    private static DatabaseConnection instance;

    // Atributo da classe para armazenar a conexão com o BD
    private Connection connection;

    // construtor privado, dentro dele criamos a conexão com o BD
    private DatabaseConnection()
    {
        try
        {
            String url = "jdbc:firebirdsql:127.0.0.1/3050:C:\\banco_de_dados\\AULAS_JAVA.FDB";
            String usuario = "SYSDBA";
            String senha = "masterkey";

            Class.forName("org.firebirdsql.jdbc.FBDriver");

            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // método público que retorna a conexão com o BD
    public Connection getConnection()
    {
        return connection;
    }

    // método para retonar a instância da classe
    public static DatabaseConnection getInstance()
    {
        // caso a instância seja nula, criamos uma nova
        if (instance == null)
        {
            instance = new DatabaseConnection();
        } else
        {
            try
            {
                // caso a conexão esteja fechada, criamos uma nova
                if (instance.getConnection().isClosed())
                {
                    instance = new DatabaseConnection();
                }
            } catch (SQLException ex)
            {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }
}
