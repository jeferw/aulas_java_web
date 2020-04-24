package javathreads;

public class RunnableStop implements Runnable
{

    @Override
    public void run()
    {
        boolean executando = true;

        int i = 0;
        while (executando)
        {
            executando = !Thread.currentThread().isInterrupted();

            System.out.println("Executando: " + executando + " Contador: " + i);
            i++;
        }

        System.out.println("Fim da contagem!");
    }
}
