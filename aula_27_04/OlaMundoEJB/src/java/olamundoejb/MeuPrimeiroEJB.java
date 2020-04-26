package olamundoejb;

import javax.ejb.Stateless;

@Stateless
public class MeuPrimeiroEJB
{

    public String digaMeuNome(String meuNome)
    {
        return "Meu nome é " + meuNome + "!";
    }
}
