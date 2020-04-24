package dao;

import dbconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Comentario;
import model.Publicacao;

public class ComentarioDAO
{

    Connection con;

    public ComentarioDAO()
    {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void salvar(Comentario comentario) throws SQLException
    {
        String sql = " "
                + " INSERT INTO COMENTARIO (IDPUBLICACAO, COMENTARIO, DATA, HORA) VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, comentario.getPublicacao().getIdPublicacao());
            ps.setString(2, comentario.getComentario());
            ps.setDate(3, new java.sql.Date(comentario.getDataTime().getTime()));
            ps.setTime(4, new java.sql.Time(comentario.getDataTime().getTime()));

            ps.execute();
        }
    }

    public List<Comentario> buscarComentariosPublicacao(Publicacao publicacao) throws SQLException
    {
        List<Comentario> comentarios = new ArrayList<>();

        String sql = " "
                + " SELECT COMENTARIO.IDCOMENTARIO, COMENTARIO.IDPUBLICACAO, COMENTARIO.COMENTARIO, COMENTARIO.DATA, COMENTARIO.HORA "
                + " FROM COMENTARIO "
                + " WHERE COMENTARIO.IDPUBLICACAO = ? "
                + " ORDER BY COMENTARIO.DATA DESC, COMENTARIO.HORA DESC";

        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setInt(1, publicacao.getIdPublicacao());
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                Comentario comentario = new Comentario();

                comentario.setIdComentario(rs.getInt("IDPUBLICACAO"));
                comentario.setComentario(rs.getString("COMENTARIO"));

                comentario.setPublicacao(publicacao);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(rs.getDate("DATA"));

                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(rs.getTime("HORA"));

                calendar.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
                calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));
                calendar.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));

                comentario.setDataTime(calendar.getTime());

                comentarios.add(comentario);
            }
            rs.close();
        }

        return comentarios;
    }
}
