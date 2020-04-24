package javathreads;

public class MainExemploRunnable
{

    public static void main(String[] args)
    {
        Runnable paralelo = new RunnableOlaMundo();
        paralelo.run();

        System.out.println("Thread principal: " + Thread.currentThread().getName());
    }
}
