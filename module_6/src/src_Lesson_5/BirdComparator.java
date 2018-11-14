package module_6.src.src_Lesson_5;

import java.util.Comparator;

public class BirdComparator implements Comparator<Bird> {
    @Override
    public int compare(Bird o1, Bird o2) {
        return (int) Math.round(o1.getWeight() - o2.getWeight());
    }
}
