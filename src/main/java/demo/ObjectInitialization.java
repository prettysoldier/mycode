package demo;


public class ObjectInitialization {
    static{
        a = 2;
    }
    static int a = 1;

    {
        i = 2;
    }
    int i = 1;

    public static void main (String[] args) {
        System.out.println(a);
        System.out.println(new ObjectInitialization().i);
    }
}


