package module_13;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Lessons {

    public static void main(String[] args) throws IOException {
        lesson_2();
    }

    private static void lesson_2() throws IOException {
        // Урок 2. Оптимизация по времени
        //
        //- В проекте CarNumberGenerator внести сделанные в видео изменения и попробовать его дополнительно
        // оптимизировать, реализовыв вывод номеров одновременно в несколько файлов из нескольких потоков.
        // Измерить время, определить, насколько программа стала работать быстрее и объяснить,

        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("module_13/res/CarNumberGenerator/res/numbers.txt");

        StringBuilder buffer = new StringBuilder();
        int bufferSize = 1_000_000;

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            //int regionCode = 199;
            for (int regionCode = 100; regionCode < 101; regionCode++)
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters) {
                        if (buffer.length() > bufferSize){
                            long startWrite = System.currentTimeMillis();
                            writer.write(buffer.toString().getBytes());
                            System.out.println(" write " + (System.currentTimeMillis() - startWrite) + " ms");
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


    private static void lesson_3(){
        // Урок 3. Оптимизация по памяти
        //
        //- Написать просмотрщик (или, лучше, редактор) больших файлов. Он должен уметь просматривать
        // файлы любого размера, не занимая при этом много оперативной памяти. Для примера файла можно
        // использовать файл размером 1,5 Gb, имеющийся в проекте VoteAnalyzer.

    }


    private static void lesson_4(){
        // Урок 4. Оптимизация работы с базами данных
        //
        //- Повторить сделанное в проекте VoteAnalyzer и попытаться его дополнительно оптимизировать по времени.
        // Также поискать в нём ошибки проектирования и реализации и, если такие есть, исправить их.
        // Попробовать обработать файл размером 1,5 Gb, имеющийся в данном проекте. Убедиться,
        // что программа не “вылетает” по памяти и измерить время выполнения.


    }


}

