package dao;

import dbconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MCurso;

public class CursoDAO
{

    Connection con;

    //método construtor
    public CursoDAO()
    {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void salvar(MCurso curso) throws SQLException
    {
        String sql = ""
                + " INSERT INTO CURSO (CURSO, TURNO, QTDEALUNOS, DATA) "
                + "            VALUES (?, ?, ?, ?);";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, curso.getCurso());
        ps.setString(2, curso.getTurno());
        ps.setInt(3, curso.getQtdeEstudantes());
        ps.setDate(4, new java.sql.Date(curso.getData().getTime()));

        ps.execute();

        ps.close();
    }

    public void editar(MCurso curso) throws SQLException
    {
        String sql = ""
                + " UPDATE CURSO "
                + " SET CURSO = ?, "
                + "     TURNO = ?, "
                + "     QTDEALUNOS = ?,"
                + "     DATA = ?"
                + " WHERE CURSO.ID = ?;"
                + "";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, curso.getCurso());
        ps.setString(2, curso.getTurno());
        ps.setInt(3, curso.getQtdeEstudantes());
        ps.setDate(4, new java.sql.Date(curso.getData().getTime()));
        ps.setInt(5, curso.getId());

        ps.execute();

        ps.close();
    }

    public void excluir(MCurso curso) throws SQLException
    {
        String sql = "DELETE FROM CURSO WHERE CURSO.ID = ?;";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, curso.getId());
        ps.execute();

        ps.close();
    }

    public List<MCurso> buscarTodosCursos() throws SQLException
    {
        List<MCurso> listaCursos = new ArrayList<>();

        String sql = ""
                + " SELECT CURSO.ID, CURSO.CURSO, CURSO.TURNO, CURSO.QTDEALUNOS, CURSO.DATA "
                + " FROM CURSO  "
                + " ORDER BY CURSO.ID";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            MCurso curso = new MCurso();

            curso.setId(rs.getInt("ID"));
            curso.setCurso(rs.getString("CURSO"));
            curso.setTurno(rs.getString("TURNO"));
            curso.setQtdeEstudantes(rs.getInt("QTDEALUNOS"));
            curso.setData(rs.getDate("DATA"));

            listaCursos.add(curso);
        }

        rs.close();
        ps.close();

        return listaCursos;
    }

    public MCurso buscarCurso(int idCurso) throws SQLException
    {
        MCurso curso = new MCurso();

        String sql = ""
                + " SELECT CURSO.ID, CURSO.CURSO, CURSO.TURNO, CURSO.QTDEALUNOS, CURSO.DATA "
                + " FROM CURSO "
                + " WHERE CURSO.ID = ? ";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idCurso);

        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            curso.setId(rs.getInt("ID"));
            curso.setCurso(rs.getString("CURSO"));
            curso.setTurno(rs.getString("TURNO"));
            curso.setQtdeEstudantes(rs.getInt("QTDEALUNOS"));
            curso.setData(rs.getDate("DATA"));
        }

        rs.close();
        ps.close();

        return curso;
    }
}
