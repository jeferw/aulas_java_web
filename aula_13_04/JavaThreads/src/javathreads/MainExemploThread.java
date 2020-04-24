package javathreads;

public class MainExemploThread
{

    public static void main(String[] args)
    {
        Runnable paralelo = new RunnableOlaMundo();
        Thread thread = new Thread(paralelo);

        thread.start();

        System.out.println("Thread principal: " + Thread.currentThread().getName());
    }
}
