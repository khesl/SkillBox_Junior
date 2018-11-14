package module_6.src.src_Lesson_6;

import Utils.ConsoleColor;

public class Chair extends Furniture{
    private int seatAmount;

    protected Chair(Furniture furniture, int seatAmount){
        super(furniture);
        this.seatAmount = seatAmount;
    }
    public Chair(Double length, Double width, Double height, Double weight, int styleId, String material, int seatAmount){
        super(length, width, height, weight, styleId, material);
        this.seatAmount = seatAmount;
    }
    @Override
    public String getDescription() {
        return super.getDescription() + ", placeAmount: " + ConsoleColor.setColor(String.valueOf(getSeatAmount()), ConsoleColor.Color.ANSI_BLUE);
    }

    public int getSeatAmount(){ return seatAmount; }
}
