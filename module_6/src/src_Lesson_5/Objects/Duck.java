package module_6.src.src_Lesson_5.Objects;

import Utils.ConsoleColor;
import module_6.src.src_Lesson_5.Bird;
import module_6.src.src_Lesson_5.Flying;
import module_6.src.src_Lesson_5.WaterBird;

public class Duck extends Bird implements Flying, WaterBird {

    public void swim(){

    }

    @Override
    public void eat() {
        System.out.println("Утка кушает.");
    }

    @Override
    public void description() {
        System.out.format("%-25s %-30s ", ConsoleColor.setColor("Утка "+ getName(), ConsoleColor.Color.ANSI_BLUE), ConsoleColor.setColor("(вес:" + getWeight() + ")", ConsoleColor.Color.ANSI_YELLOW));
    }

    @Override
    public void move() {

    }

    @Override
    public void voice() {
        System.out.println("Кря-Кря");
    }

    @Override
    public void fly() {

    }
}
