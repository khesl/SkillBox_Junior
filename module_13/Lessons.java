package module_13;

import module_13.Lessons_.Lesson_2;
import module_13.Lessons_.Lesson_3;

import java.io.IOException;

public class Lessons {

    public static void main(String[] args) throws IOException, InterruptedException {
        //lesson_2();
        lesson_3();
        //lesson_4();
    }

    private static void lesson_2() throws IOException, InterruptedException {
        // Урок 2. Оптимизация по времени
        //
        //- В проекте CarNumberGenerator внести сделанные в видео изменения и попробовать его дополнительно
        // оптимизировать, реализовыв вывод номеров одновременно в несколько файлов из нескольких потоков.
        // Измерить время, определить, насколько программа стала работать быстрее и объяснить,

        /**
         * задание выполнено в отдельном файле по уроку
         * {@link module_13.Lessons_.Lesson_2}
         * */
        Lesson_2 less = new Lesson_2();
        less.lesson_2(0, 10, 10);
    }


    private static void lesson_3(){
        // Урок 3. Оптимизация по памяти
        //
        //- Написать просмотрщик (или, лучше, редактор) больших файлов. Он должен уметь просматривать
        // файлы любого размера, не занимая при этом много оперативной памяти. Для примера файла можно
        // использовать файл размером 1,5 Gb, имеющийся в проекте VoteAnalyzer.

        /**
         * задание выполнено в отдельном файле по уроку
         * {@link module_13.Lessons_.Lesson_3}
         * */
        Lesson_3 less = new Lesson_3();
        less.lesson_3();
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

