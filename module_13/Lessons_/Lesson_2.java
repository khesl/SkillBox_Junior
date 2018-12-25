package module_13.Lessons_;

import module_13.Lessons_.res.MyFileWriter;
import module_13.Lessons_.res.NumGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Ниже результаты тестирования при формировании 200мб данных. используя многопоточность прирост в скорости в 3 раза,
 *  предполагаю запись 5-ю и более потоками достигает лимита записи на жёсткий диск,
 *  поэтому блокируется запись других потоков.
 *
 *  средняя скорость записи 5мб/сек, быстрее не получалось в лююбом раскладе.
 *
 * Запись в 5 файлов 5-ю потоками
 *
 * Generator(3) start.from '600' to '800'
 * Generator(1) start.from '200' to '400'
 * Generator(0) start.from '0' to '200'
 * Generator(4) start.from '800' to '1000'
 * Generator(2) start.from '400' to '600'
 * Generator(1) is done. 20842 ms
 * Generator(2) is done. 20884 ms
 * Generator(3) is done. 20893 ms
 * Generator(4) is done. 21121 ms
 * Generator(0) is done. 21178 ms
 * 21290 ms
 *
 * Запись в 10 файлов 10-ю потоками
 *
 * Generator(1) start.from '100' to '200'
 * Generator(0) start.from '0' to '100'
 * Generator(4) start.from '400' to '500'
 * Generator(5) start.from '500' to '600'
 * Generator(3) start.from '300' to '400'
 * Generator(7) start.from '700' to '800'
 * Generator(8) start.from '800' to '900'
 * Generator(2) start.from '200' to '300'
 * Generator(9) start.from '900' to '1000'
 * Generator(6) start.from '600' to '700'
 * Generator(0) is done. 19342 ms
 * Generator(3) is done. 19797 ms
 * Generator(4) is done. 19813 ms
 * Generator(6) is done. 19747 ms
 * Generator(7) is done. 20053 ms
 * Generator(9) is done. 20059 ms
 * Generator(5) is done. 20143 ms
 * Generator(1) is done. 20192 ms
 * Generator(8) is done. 20209 ms
 * Generator(2) is done. 20186 ms
 * 20470 ms
 *
 * Запись в 1 файл 1-м потоком
 *
 * 61983 ms
 * */
public class Lesson_2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Lesson_2 less = new Lesson_2();

        less.lesson_2(0, 100, 10);
    }

    /**
     * @param minGenerateValue  - in period [0..100]%   - start percentage from all period of auto number
     * @param maxGenerateValue  - in period [0..100]%   - end percentage from all period of auto number
     * @param threadCount       - count of usable threads
     * */
    public void lesson_2(int minGenerateValue, int maxGenerateValue, int threadCount) throws IOException, InterruptedException {
        if (minGenerateValue < 0 || minGenerateValue > maxGenerateValue || maxGenerateValue > 100)
            throw new IllegalArgumentException("Wrong diapason of values");
        // Урок 2. Оптимизация по времени
        //
        //- В проекте CarNumberGenerator внести сделанные в видео изменения и попробовать его дополнительно
        // оптимизировать, реализовыв вывод номеров одновременно в несколько файлов из нескольких потоков.
        // Измерить время, определить, насколько программа стала работать быстрее и объяснить,

        long start = System.currentTimeMillis();

        maxGenerateValue = 1000 * maxGenerateValue;

        List<NumGenerator> threads = new ArrayList<>();
        int curGenerateValue = 0;
        for (int i = 0; i < threadCount; i++){
            threads.add(new NumGenerator(curGenerateValue,
                    (curGenerateValue + (maxGenerateValue-minGenerateValue)/threadCount),
                    "Generator(" + i + ")", "module_13/res/CarNumberGenerator/res/numbers" + i +".txt"));
            curGenerateValue += (maxGenerateValue-minGenerateValue)/threadCount;
        }

        for (NumGenerator generator : threads){
            generator.start();
        }
        for (NumGenerator generator : threads){
            generator.join();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }




}
