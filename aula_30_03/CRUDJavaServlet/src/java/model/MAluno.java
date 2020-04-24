package model;

import java.util.Date;

public class MAluno
{

    private int id;
    private MCurso curso;
    private String nome;
    private Date dataNascimento;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public MCurso getCurso()
    {
        return curso;
    }

    public void setCurso(MCurso curso)
    {
        this.curso = curso;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
}
