package module_13.res.CarNumberGenerator.src;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by Danya on 20.02.2016.
 */
public class Loader
{
    public static void main(String[] args) throws Exception {
        //baseLoader();
        //optimizeLoader();
        moreOptimizeLoader();
    }

    private static void baseLoader() throws Exception{
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("module_13/res/CarNumberGenerator/res/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            int regionCode = 199;
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters)
                    {
                        String carNumber = firstLetter + padNumber(number, 3) +
                                secondLetter + thirdLetter + padNumber(regionCode, 2);
                        writer.write(carNumber.getBytes());
                        writer.write('\n');
                    }
                }
            }
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void optimizeLoader() throws Exception{
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("module_13/res/CarNumberGenerator/res/numbers.txt");

        StringBuilder buffer = new StringBuilder();
        int bufferSize = 1_000_000;

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            int regionCode = 199;
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters) {
                        if (buffer.length() > bufferSize){
                            writer.write(buffer.toString().getBytes());
                            buffer = new StringBuilder();
                        }

                        buffer.append(firstLetter);
                        if (number < 10) buffer.append("00");
                        else if (number < 100) buffer.append("0");
                        buffer.append(number)
                                .append(secondLetter)
                                .append(thirdLetter);

                        if (regionCode < 10) buffer.append("0");
                        buffer.append(regionCode)
                                .append('\n');
                    }
                }
            }
        }

        writer.write(buffer.toString().getBytes());
        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static void moreOptimizeLoader() throws Exception{
        long start = System.currentTimeMillis();

        PrintWriter writer = new PrintWriter("module_13/res/CarNumberGenerator/res/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            //int regionCode = 199;
            for (int regionCode = 100; regionCode < 110; regionCode++)
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters) {

                        String carNumber = firstLetter + padNumber(number, 3) +
                                secondLetter + thirdLetter + padNumber(regionCode, 2);
                        writer.write(carNumber);
                    }
                }
            }
        }

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength)
    {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for(int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
    }
}
