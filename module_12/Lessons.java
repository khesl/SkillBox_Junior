package module_12;

import module_10.Lessons_.src.MyExplorerSimple;
import module_12.Lessons_.src.StopWatch;
import module_12.Lessons_.src.StopWatchClass;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lessons {

    private StopWatchClass stopWatchClass = new StopWatchClass();
    public static void main(String[] args) {
        Lessons less = new Lessons();
        /** подзадачи разбиты внутри методов. */

        System.out.println(less.stopWatchClass.getFormattedTime(new SimpleDateFormat("HH:mm:ss.S")));

        less.Lesson_2();
    }

    private JFrame frame;
    private StopWatch stopWatch;
    private void Lesson_2(){
        // секундомер с интерфейсом, 2 кнопки (start/pause, stop)

        stopWatch = new StopWatch(this);
        frame = createMainFrame(stopWatch.getRootPanel(), "Проводник", new Dimension(310,110));
        //frame.setUndecorated(true); // убрать системные бордеры у окна!

        frame.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame.setVisible(true);
    }


    private static JFrame createMainFrame(JPanel panel, String formName, Dimension dimension){
        JFrame frame = new JFrame();

        frame.setContentPane(panel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width, dimension.height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(formName);

        return frame;
    }
}
