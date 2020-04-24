package javathreads;

public class Contadora implements Runnable
{

    private String nome;

    public Contadora(String nome)
    {
        this.nome = nome;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10000; i++)
        {
            System.out.println("Contadora: " + this.nome + " Valor: " + i);
        }

        System.out.println("Contadora: " + this.nome + " FIM");
    }
}
