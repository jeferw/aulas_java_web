package javathreads;

public class RunnableOlaMundo implements Runnable
{

    @Override
    public void run()
    {
        System.out.println("Olá Mundo em paralelo!");
        System.out.println("Eu sou a thread: " + Thread.currentThread().getName());
    }
}
