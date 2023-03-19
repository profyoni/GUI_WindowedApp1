public class Misc {
    int max(int a, int b)
    {
        return a>b?a:b;
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
        int x =max(7,3,8,2,0,4,875);
    }
}
