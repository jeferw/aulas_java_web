package javathreads;

public class MainExemploThreadStop
{

    public static void main(String[] args)
    {
        Runnable paralelo = new RunnableStop();

        Thread thread = new Thread(paralelo);
        thread.start();

        try
        {
            Thread.sleep(500);
        } catch (InterruptedException ex)
        {
        }

        thread.interrupt();
    }
}
