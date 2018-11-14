package module_6.src.src_Lesson_6;

import Utils.ConsoleColor;

import java.util.Collection;

public abstract class Furniture {
    private int styleId;
    private String material;
    private Double height; // Высота
    private Double width; // Ширина
    private Double length; // Длина
    private Double weight; // Вес

    public Furniture(Double length, Double width, Double height, Double weight, int styleId, String material){
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.styleId = styleId;
        this.material = material;
    }
    public Furniture(Furniture furniture){
        this.length = furniture.length;
        this.width = furniture.width;
        this.height = furniture.height;
        this.weight = furniture.weight;
        this.styleId = furniture.styleId;
        this.material = furniture.material;
    }

    /** Volume objects dimensions (x*y*z)
     * @return Double
     * */
    public final Double getSize3(){
        return height*width*length;
    }
    /** Square objects dimensions (x*y)
     * @return Double
     * */
    public final Double getSize2(){
        return width*length;
    }

    public int getStyleId() { return styleId; }
    public String getMaterial() { return material; }
    public Double getHeight() { return height; }
    public Double getWidth() { return width; }
    public Double getLength() { return length; }
    public Double getWeight() { return weight; }

    public String getDescription() {
        return this.getClass().getName()
                + " length: " + ConsoleColor.setColor(getLength().toString(), ConsoleColor.Color.ANSI_BLUE)
                + ", width: " + ConsoleColor.setColor(getWidth().toString(), ConsoleColor.Color.ANSI_BLUE)
                + ", height: " + ConsoleColor.setColor( getHeight().toString(), ConsoleColor.Color.ANSI_BLUE)
                + ", weight: " + ConsoleColor.setColor(getWeight().toString(), ConsoleColor.Color.ANSI_BLUE)
                + ", styleId: " + ConsoleColor.setColor(String.valueOf(getStyleId()), ConsoleColor.Color.ANSI_BLUE)
                + ", material: " + ConsoleColor.setColor(getMaterial(), ConsoleColor.Color.ANSI_BLUE);
    }

    public final boolean isComplect(Furniture o){
        return styleId == o.getStyleId() && material.equals(o.getMaterial()) && !getClass().getName().equals(o.getClass().getName());
    }

    public static boolean isFullComplect(Collection<Furniture> furnitures, Table table){
        int complectSize = table.getPlaceAmount();
        for (Furniture furniture : furnitures)
            if (furniture.isComplect(table)) {
                System.out.println(ConsoleColor.setColor("\tчасть комплекта: " + furniture.getDescription(), ConsoleColor.Color.ANSI_YELLOW));
                complectSize -= ((Chair)furniture).getSeatAmount();
                System.out.println(ConsoleColor.setColor("\t\tдля комплекта не хватает: '" + complectSize + "'", ConsoleColor.Color.ANSI_RED));
            }
        return complectSize <= 0 ? true : false;
    }

}
