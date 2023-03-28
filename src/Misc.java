import java.util.HashMap;

class Person {
    static int population;
    Person(){
        Person.population++;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        population--;
    }
}

public class Misc {
    private static HashMap<String,String> d = new HashMap<>();

    static
    {
        d.put("OS", "MAc");
        d.put("ip", "1.2.3.4");
    }

    static String get(String key)
    {
        return d.get(key);
    }

    int max(int a, int b)
    {
        return a>b?a:b;
    }

    int max(int a, int b, int c)
    {
        return a>b? max(a,c):max(b,c);
    }
    int max(int a, int b, int c, int d)
    {
        return max(max(a,b),max(c,d));
    }










    // variable args - varargs
    public static int  max(int ...list)
    {
        int max = list[0];
        for (int i=1;i<list.length;i++)
            if (list[i]>max)
                max = list[i];
        return max;
    }

    // pass command line args to main
    public static void main(String[] args) {
        int x =max(7,3,8,2,0,-7,3,3,3,3,3,3,3,3,3,3,3,3,3,3,34,4,875);
    }
}
