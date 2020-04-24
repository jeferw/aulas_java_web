package model;

import java.util.Date;

public class Comentario
{

    private int idComentario;
    private Publicacao publicacao;
    private String comentario;
    private Date dataTime;

    public int getIdComentario()
    {
        return idComentario;
    }

    public void setIdComentario(int idComentario)
    {
        this.idComentario = idComentario;
    }

    public Publicacao getPublicacao()
    {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao)
    {
        this.publicacao = publicacao;
    }

    public String getComentario()
    {
        return comentario;
    }

    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }

    public Date getDataTime()
    {
        return dataTime;
    }

    public void setDataTime(Date dataTime)
    {
        this.dataTime = dataTime;
    }

}
