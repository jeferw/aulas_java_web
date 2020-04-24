package dao;

import dbconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import model.Publicacao;

public class PublicacaoDAO
{

    Connection con;

    public PublicacaoDAO()
    {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void salvar(Publicacao publicacao) throws SQLException
    {
        String sql = " "
                + " INSERT INTO PUBLICACAO (PUBLICACAO, DATA, HORA) VALUES (?, ?, ?);";

        try (PreparedStatement ps = con.prepareStatement(sql))
        {
            ps.setString(1, publicacao.getPublicacao());
            ps.setDate(2, new java.sql.Date(publicacao.getDataTime().getTime()));
            ps.setTime(3, new java.sql.Time(publicacao.getDataTime().getTime()));

            ps.execute();
        }
    }

    public List<Publicacao> buscarTodosPublicacoes() throws SQLException
    {
        List<Publicacao> publicacoes = new ArrayList<>();

        String sql = " "
                + " SELECT PUBLICACAO.IDPUBLICACAO, PUBLICACAO.PUBLICACAO, PUBLICACAO.DATA, PUBLICACAO.HORA "
                + " FROM PUBLICACAO    "
                + " ORDER BY PUBLICACAO.DATA DESC, PUBLICACAO.HORA DESC";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            Publicacao publicacao = new Publicacao();

            publicacao.setIdPublicacao(rs.getInt("IDPUBLICACAO"));
            publicacao.setPublicacao(rs.getString("PUBLICACAO"));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(rs.getDate("DATA"));

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(rs.getTime("HORA"));

            calendar.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));
            calendar.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));

            publicacao.setDataTime(calendar.getTime());

            publicacoes.add(publicacao);
        }
        rs.close();
        ps.close();

        return publicacoes;
    }

}
