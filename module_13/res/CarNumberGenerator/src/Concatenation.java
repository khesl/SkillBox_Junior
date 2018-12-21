package module_13.res.CarNumberGenerator.src;

/**
 * Created by Danya on 20.02.2016.
 */
public class Concatenation
{
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        /*String str = "";
        for(int i = 0; i < 20_000; i++)
        {
            str += "some text some text some text";
        }*/

        StringBuilder str = new StringBuilder();
        for(int i = 0; i < 20_000; i++)
        {
            str.append("some text some text some text");
        }

        System.out.println("length: " + str.length());

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
