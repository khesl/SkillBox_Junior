package module_13.Lessons_;

import Utils.MyUtils;
import module_13.Lessons_.res.MyFileEditor;

import javax.swing.*;
import java.awt.*;

public class Lesson_3 {

    public static void main(String[] args) {
        Lesson_3 less = new Lesson_3();

        less.lesson_3();

    }

    private JFrame frame;
    private MyFileEditor myFileEditor;
    public void lesson_3(){
        // Урок 3. Оптимизация по памяти
        //
        //- Написать просмотрщик (или, лучше, редактор) больших файлов. Он должен уметь просматривать
        // файлы любого размера, не занимая при этом много оперативной памяти. Для примера файла можно
        // использовать файл размером 1,5 Gb, имеющийся в проекте VoteAnalyzer.

        // у нас должен быть метод, читающий огромный файл. Как? побайтово, мы должны знать некий буфер экрана,
        // общее кол-во байт файла. методы возврата текущего положения каретки, можно в %

        myFileEditor = new MyFileEditor(this);
        frame = MyUtils.createMainFrame(myFileEditor.getRootPanel(), "редактор файлов", new Dimension(640,480));
        //frame.setUndecorated(true); // убрать системные бордеры у окна!

        frame.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame.setVisible(true);
    }
}
