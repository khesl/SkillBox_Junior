package module_5;

public class Lessons {

    public static void main(String[] args) {

        Lesson_1();
    }

    private static void Lesson_1(){
        System.out.println("\t\t--- First task ---");
        //- Повторить создание массива и заполнение цветами радуги, как показано в видео, а затем написать код, переворачивающий этот массив.
        String[] rainbow = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый"};

        System.out.print("Straight rainbow: \t");
        for (String str : rainbow)
            System.out.print(str + ", ");
        System.out.println();
        System.out.print("Reverse rainbow: \t");
        for (int i = rainbow.length-1; i > 0; i--)
            System.out.print(rainbow[i] + ", ");
        System.out.println();
        System.out.print("New Reverse rainbow: \t");
        String[] back_rainbow = new String[rainbow.length];
        for (int i = 0; i < rainbow.length; i++)
            back_rainbow[rainbow.length-1-i] = rainbow[i];
        for (String str : back_rainbow)
            System.out.print(str + ", ");

        System.out.println();
        System.out.println("\t\t--- Second task ---");
        //- Создать и распечатать массив серий паспортов гражданина РФ.
            // PS. я не гражданин РФ, поэтому не знаком с пинципами генерации Паспорта, номера брал с первого примера.
        String[] pasport = new String[10];
        for (int i = 0; i< pasport.length; i++) {
            //4507...
            int issNum = (int)(30 + 20 * Math.random());
            int issYear = (int)(18 * Math.random());
            int passNum = (int)(10000 + 970000 * Math.random());
            pasport[i] = String.valueOf(issNum) + " " + (issYear < 10 ? "0" + String.valueOf(issYear): String.valueOf(issYear)) + " " + String.valueOf(passNum);
        }
        for (String str : pasport)
            System.out.println("Passport num: " + str);

        System.out.println();
        System.out.println("\t\t--- Third task ---");
        //- Создать массив массивов клеток на шахматной доске и распечатать так,
            // чтобы в консоли названия клеток были напечатаны в форме шахматной доски.
        String[][] chessDesk = new String[8][8];
        for (int i = 0; i < chessDesk.length; i++){
            for (int j = 0; j < chessDesk[i].length; j++)
                if ((i+j)%2 == 0) chessDesk[i][j] = "[x]";
                else chessDesk[i][j] = "[ ]";
        }
        System.out.println("Chess desk ([x] - black, [ ] - white)");
        String st = "\t";
        for (int i = 1; i< 9; i++)
            st += " " +i + " ";
        System.out.println(st);
        //for (String[] strs : chessDesk) { for (String str : strs) System.out.print(str); System.out.println(); }
        for (int i = 0; i < chessDesk.length; i++) {
            System.out.print("  " +(char)(97+i) + "\t");
            for (String str : chessDesk[i])
                System.out.print(str);
            System.out.println();
        }
    }

}
