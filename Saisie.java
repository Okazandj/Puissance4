import java.io.*;

class Saisie
{
  public static char litchar ()
  {
    char	carac = 'q';
    String	x = "";
    BufferedReader d = new BufferedReader (new InputStreamReader(System.in));

    try
    {
      x = d.readLine();
    }
    catch (Exception e) { }
    carac = x.charAt (0);
    return (carac);
  }


  public static String litexte ()
  {
    String	x = "";
    BufferedReader d = new BufferedReader (new InputStreamReader(System.in));

    try
    {
      x = d.readLine();
    }
    catch (Exception e) { }
    return (x);
  }

  public static int litentier ()
  {
    int		val = 0;
    Integer	y;
    String	x = "";
    BufferedReader d = new BufferedReader (new InputStreamReader(System.in));

    try
    {
      x = d.readLine();
    }
    catch (Exception e) { }
    y = new Integer (x);
    val = y.intValue ();
    return (val);
  }

  public static double litreel ()
  {
    double	val = 0.;
    Double	y;
    String	x = "";
    BufferedReader d = new BufferedReader (new InputStreamReader(System.in));

    try
    {
      x = d.readLine();
    }
    catch (Exception e) { }
    y = new Double (x);
    val = y.doubleValue ();
    return (val);
  }
}