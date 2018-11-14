package module_6.src.src_Lesson_5.Objects;

import Utils.ConsoleColor;
import module_6.src.src_Lesson_5.Bird;
import module_6.src.src_Lesson_5.Flying;

public class Chicken extends Bird implements Flying {
    @Override
    public void eat() {
        System.out.println("Курица клюёт.");
    }

    @Override
    public void move() {

    }

    @Override
    public void description() {
        System.out.format("%-25s %-30s ", ConsoleColor.setColor("Курица "+ getName(), ConsoleColor.Color.ANSI_BLUE), ConsoleColor.setColor("(вес:" + getWeight() + ")", ConsoleColor.Color.ANSI_YELLOW));
    }

    @Override
    public void voice() {
        System.out.println("Ко-ко-ко");
    }

    @Override
    public void fly() {

    }
}
