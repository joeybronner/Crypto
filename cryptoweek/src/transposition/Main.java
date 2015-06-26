package transposition;

/*
public class Main {
    public static void main(String[] args) {
        System.out.println("VOICI UN MESSAGE CLAIR");
        System.out.println(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"));
        System.out.println(TranspoCypher.decode(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"), TranspoCypher.key));
        TranspoCypher.cipher(TranspoCypher.encode("VOICI UN MESSAGE CLAIR"));
    }
}
*/
import java.util.ArrayList;
import java.util.TreeSet;


public class Main

{
    static ArrayList<int[]> list = new ArrayList<int[]>();
    static void permute(int[] a, int k)
    {
        if (k == a.length)
        {
            System.out.println("toto");
            list.add(a.clone());
        }
        else
        {
            for (int i = k; i < a.length; i++)
            {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                permute(a, k + 1);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }


    public static void main(String args[])

    {
        int N = 25;
        TreeSet<Integer> sizes = factors(N);
        for (int size: sizes) {
            int[] sequence = range(size);
            permute(sequence, 0);
        }
    }
    public static int[] range(int stop)
    {
        int[] result = new int[stop];

        for(int i=0;i<stop;i++)
            result[i] = i;

        return result;
    }

    public static TreeSet<Integer> factors(int n)
    {
        TreeSet<Integer> factors = new TreeSet<Integer>();
        factors.add(n);
        for(long test = n - 1; test >= Math.sqrt(n); test--)
            if(n % test == 0)
            {
                factors.add((int) test);
                factors.add((int) (n / test));
            }
        return factors;
    }

}