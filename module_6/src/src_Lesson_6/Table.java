package module_6.src.src_Lesson_6;

import Utils.ConsoleColor;

public class Table extends Furniture {
    private int placeAmount;

    protected Table(Furniture furniture, int placeAmount){
        super(furniture);
        this.placeAmount = placeAmount;
    }
    public Table(Double length, Double width, Double height, Double weight, int styleId, String material, int placeAmount){
        super(length, width, height, weight, styleId, material);
        this.placeAmount = placeAmount;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", placeAmount: " + ConsoleColor.setColor(String.valueOf(getPlaceAmount()), ConsoleColor.Color.ANSI_BLUE);
    }

    public int getPlaceAmount() { return placeAmount; }
}
