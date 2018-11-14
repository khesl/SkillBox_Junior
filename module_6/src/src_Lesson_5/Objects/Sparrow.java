package module_6.src.src_Lesson_5.Objects;

import Utils.ConsoleColor;
import module_6.src.src_Lesson_5.Bird;
import module_6.src.src_Lesson_5.Flying;

/**
 * воробушек
 * */
public class Sparrow extends Bird implements Flying {

    @Override
    public void eat() {
        System.out.println("Воробушек кушает.");
    }

    @Override
    public void description() {
        System.out.format("%-25s %-30s ", ConsoleColor.setColor("Воробушек "+ getName(), ConsoleColor.Color.ANSI_BLUE), ConsoleColor.setColor("(вес:" + getWeight() + ")", ConsoleColor.Color.ANSI_YELLOW));
    }

    @Override
    public void move() {

    }

    @Override
    public void voice() {
        System.out.println("Чирик-Чирик");
    }

    @Override
    public void fly() {

    }

}
