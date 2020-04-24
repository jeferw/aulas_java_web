package dao;

import dbconnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MAluno;
import model.MCurso;

public class AlunoDAO
{

    Connection con;

    public AlunoDAO()
    {
        con = DatabaseConnection.getInstance().getConnection();
    }

    public void excluir(MAluno aluno) throws SQLException
    {
        String sql = " DELETE FROM ALUNO WHERE ALUNO.ID = ? ";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, aluno.getId());
        ps.execute();

        ps.close();
    }

    public void editar(MAluno aluno) throws SQLException
    {
        String sql = " "
                + " UPDATE ALUNO "
                + " SET ALUNO.IDCURSO = ?, "
                + "     ALUNO.NOME = ?, "
                + "     ALUNO.DATANASCIMENTO = ? "
                + " WHERE (ALUNO.ID = ?); ";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, aluno.getCurso().getId());
        ps.setString(2, aluno.getNome());
        ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
        ps.setInt(4, aluno.getId());

        ps.execute();

        ps.close();
    }

    public void salvar(MAluno aluno) throws SQLException
    {
        String sql = " "
                + " INSERT INTO ALUNO (IDCURSO, NOME, DATANASCIMENTO) VALUES (?, ?, ?);";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, aluno.getCurso().getId());
        ps.setString(2, aluno.getNome());
        ps.setDate(3, new java.sql.Date(aluno.getDataNascimento().getTime()));
        ps.execute();

        ps.close();
    }

    public List<MAluno> buscarTodosAlunos() throws SQLException
    {
        List<MAluno> alunos = new ArrayList<>();

        String sql = " "
                + " SELECT ALUNO.ID, ALUNO.IDCURSO, CURSO.CURSO, ALUNO.NOME, ALUNO.DATANASCIMENTO "
                + " FROM ALUNO "
                + " INNER JOIN CURSO ON (CURSO.ID = ALUNO.IDCURSO)  ";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next())
        {
            MAluno aluno = new MAluno();
            MCurso curso = new MCurso();

            curso.setId(rs.getInt("IDCURSO"));
            curso.setCurso(rs.getString("CURSO"));

            aluno.setCurso(curso);
            aluno.setId(rs.getInt("ID"));
            aluno.setNome(rs.getString("NOME"));
            aluno.setDataNascimento(rs.getDate("DATANASCIMENTO"));

            alunos.add(aluno);
        }
        rs.close();
        ps.close();

        return alunos;
    }

    public MAluno buscarAluno(int id) throws SQLException
    {
        MCurso curso = new MCurso();
        MAluno aluno = new MAluno();

        String sql = " "
                + " SELECT ALUNO.ID, ALUNO.IDCURSO, ALUNO.NOME, ALUNO.DATANASCIMENTO "
                + " FROM ALUNO  "
                + " WHERE ALUNO.ID = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next())
        {
            curso.setId(rs.getInt("IDCURSO"));

            aluno.setCurso(curso);
            aluno.setId(rs.getInt("ID"));
            aluno.setNome(rs.getString("NOME"));
            aluno.setDataNascimento(rs.getDate("DATANASCIMENTO"));
        }
        rs.close();
        ps.close();

        return aluno;
    }
}
