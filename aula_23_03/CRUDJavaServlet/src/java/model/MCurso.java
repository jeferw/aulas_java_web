package model;

import java.util.Date;

public class MCurso
{

    private int id;
    private String curso;
    private String turno;
    private int qtdeEstudantes;
    private Date data;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCurso()
    {
        return curso;
    }

    public void setCurso(String curso)
    {
        this.curso = curso;
    }

    public String getTurno()
    {
        return turno;
    }

    public void setTurno(String turno)
    {
        this.turno = turno;
    }

    public int getQtdeEstudantes()
    {
        return qtdeEstudantes;
    }

    public void setQtdeEstudantes(int qtdeEstudantes)
    {
        this.qtdeEstudantes = qtdeEstudantes;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }
}
