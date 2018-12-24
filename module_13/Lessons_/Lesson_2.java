package module_13.Lessons_;

import module_13.Lessons_.res.MyFileWriter;
import module_13.Lessons_.res.NumGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Lesson_2 {
    private static MyFileWriter writer;

    public static void main(String[] args) throws IOException, InterruptedException {
        Lesson_2 less = new Lesson_2();

        less.lesson_2();
    }

    private void lesson_2() throws IOException, InterruptedException {
        // Урок 2. Оптимизация по времени
        //
        //- В проекте CarNumberGenerator внести сделанные в видео изменения и попробовать его дополнительно
        // оптимизировать, реализовыв вывод номеров одновременно в несколько файлов из нескольких потоков.
        // Измерить время, определить, насколько программа стала работать быстрее и объяснить,

        long start = System.currentTimeMillis();

        //writer = new MyFileWriter("module_13/res/CarNumberGenerator/res/numbers.txt");

        int minGenerateValue = 0;
        int maxGenerateValue = 200;
        int threadCount = 2;
        List<NumGenerator> threads = new ArrayList<>();
        int curGenerateValue = 0;
        for (int i = 0; i < threadCount; i++){
            threads.add(new NumGenerator(curGenerateValue,
                    (curGenerateValue + (maxGenerateValue-minGenerateValue)/threadCount),
                    1_000_000,
                    "Generator(" + i + ")", "module_13/res/CarNumberGenerator/res/numbers" + i +".txt"));
            curGenerateValue += (maxGenerateValue-minGenerateValue)/threadCount;
        }

        for (NumGenerator generator : threads){
            generator.start();
        }
        for (NumGenerator generator : threads){
            generator.join();
        }

        //writer.flushWriter();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }




}
