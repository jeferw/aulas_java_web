package desafio;

public class SingletonContador
{

    private static final SingletonContador INSTANCE = new SingletonContador();
    private int qtde;

    private SingletonContador()
    {
    }

    public static SingletonContador getInstance()
    {
        return INSTANCE;
    }

    public int getQtde()
    {
        return qtde;
    }

    public void setQtde(int qtde)
    {
        this.qtde = qtde;
    }
}
