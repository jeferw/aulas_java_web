package ejbcalcular;

import javax.ejb.Stateless;

@Stateless
public class Calcular
{

    public double somar(double num1, double num2)
    {
        return num1 + num2;
    }

    public double subtrair(double num1, double num2)
    {
        return num1 - num2;
    }

    public double multiplicar(double num1, double num2)
    {
        return num1 * num2;
    }

    public double dividir(double num1, double num2)
    {
        return num1 / num2;
    }
}
