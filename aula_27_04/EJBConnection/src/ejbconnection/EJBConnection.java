package ejbconnection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBConnection
{

    public static Object execMethod(String projetoEClasse, String metodo, Object... parametros) throws Exception
    {
        try
        {
            Object o = lookup(projetoEClasse);
            Method m = getMethod(o, metodo, parametros);

            return m.invoke(o, parametros);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NamingException ex)
        {
            throw new Exception(ex);
        }
    }

    private static Object lookup(String projetoEClasse) throws NamingException
    {
        return (new InitialContext()).lookup("java:global/" + projetoEClasse);
    }

    private static Method getMethod(Object objeto, String metodo, Object[] parametros)
    {
        Method[] metodos;
        if (objeto instanceof Class)
        {
            metodos = ((Class) objeto).getMethods();
        } else
        {
            metodos = objeto.getClass().getMethods();
        }
        for (Method metodoAtual : metodos)
        {
            if (metodoAtual.getName().equals(metodo))
            {
                Class[] mParams = metodoAtual.getParameterTypes();
                if (parametros == null && mParams.length == 0)
                {
                    return metodoAtual;
                }
                if (parametros != null && parametros.length == mParams.length)
                {
                    int i = 0;
                    while (true)
                    {
                        if (i < parametros.length)
                        {
                            if (parametros[i] != null)
                            {
                                if (mParams[i].isPrimitive())
                                {
                                    if (mParams[i].isAssignableFrom(int.class))
                                    {
                                        if (!Integer.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(long.class))
                                    {
                                        if (!Long.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(boolean.class))
                                    {
                                        if (!Boolean.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(short.class))
                                    {
                                        if (!Short.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(char.class))
                                    {
                                        if (!Character.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(double.class))
                                    {
                                        if (!Double.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(float.class))
                                    {
                                        if (!Float.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    } else if (mParams[i].isAssignableFrom(byte.class))
                                    {
                                        if (!Byte.class.isInstance(parametros[i]))
                                        {
                                            break;
                                        }
                                    }
                                } else if (!mParams[i].isInstance(parametros[i]))
                                {
                                    break;
                                }
                            }
                            i++;
                            continue;
                        }
                        return metodoAtual;
                    }
                }
            }
        }
        return null;
    }
}
