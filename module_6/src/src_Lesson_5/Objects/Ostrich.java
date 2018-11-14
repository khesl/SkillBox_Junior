package module_6.src.src_Lesson_5.Objects;

import Utils.ConsoleColor;
import module_6.src.src_Lesson_5.Bird;

/**
 * страус
 * */
public class Ostrich extends Bird {

    @Override
    public void eat() {
        System.out.println("Страус кушает.");
    }

    @Override
    public void description() {
        System.out.format("%-25s %-30s ", ConsoleColor.setColor("Страус "+ getName(), ConsoleColor.Color.ANSI_BLUE), ConsoleColor.setColor("(вес:" + getWeight() + ")", ConsoleColor.Color.ANSI_YELLOW));
        //return ConsoleColor.setColor("Страус "+ getName() + "\t", ConsoleColor.Color.ANSI_BLUE) + ConsoleColor.setColor("\t(вес:" + getWeight() + ")", ConsoleColor.Color.ANSI_YELLOW);
    }

    @Override
    public void move() {

    }

    @Override
    public void voice() {
        System.out.println("Из под земли раздается удалённый писк..");
    }


}
