package module_6.src.src_Lesson_5;

import Utils.MyUtils;

public abstract class Bird implements Vertebrate {
    private Double weight;
    private String name;

    public void walk(){
    }

    public void run(){
    }

    public Bird(){
        create();
        name = MyUtils.getRamdonName();
    }

    private void create(){
        weight = Math.random()*5000 + 100;
    }

    public abstract void eat();

    public abstract void move();

    public Double getWeight(){ return weight; }
    public String getName() { return name; }

    public int compareTo(Bird o) {
        return (int) Math.round(weight - o.getWeight());
    }

}
