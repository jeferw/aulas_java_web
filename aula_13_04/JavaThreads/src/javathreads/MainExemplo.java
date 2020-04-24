package javathreads;

public class MainExemplo
{

    public static void main(String[] args)
    {

        Thread principal = Thread.currentThread();

        System.out.println("Nome da Thread: " + principal.getName()); //Nome da Thread: main
    }
}
