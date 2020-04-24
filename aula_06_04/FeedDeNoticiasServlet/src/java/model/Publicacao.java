package model;

import java.util.Date;

public class Publicacao
{

    private int idPublicacao;
    private String publicacao;
    private Date dataTime;

    public int getIdPublicacao()
    {
        return idPublicacao;
    }

    public void setIdPublicacao(int idPublicacao)
    {
        this.idPublicacao = idPublicacao;
    }

    public String getPublicacao()
    {
        return publicacao;
    }

    public void setPublicacao(String publicacao)
    {
        this.publicacao = publicacao;
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
