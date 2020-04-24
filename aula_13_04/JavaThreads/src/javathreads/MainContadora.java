package javathreads;

public class MainContadora
{

    public static void main(String[] args)
    {
        Contadora c1 = new Contadora("01");
        Thread t1 = new Thread(c1);
        t1.start();

        Contadora c2 = new Contadora("02");
        Thread t2 = new Thread(c2);
        t2.start();
    }
}
