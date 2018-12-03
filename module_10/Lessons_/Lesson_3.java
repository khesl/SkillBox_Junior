package module_10.Lessons_;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lesson_3 {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        Lesson_3(); // преобразование формата
    }

    /** результат также записывается в файл format_3.txt
     * */
    private static void Lesson_3() throws FileNotFoundException {
        // - Написать программу, которая будет преобразовывать файлы формата “Probabilites.txt” в файлы формата “Probabilities ­ formatted.txt”.

        //	Алексей	Константин	Пётр
        //10.04.2014	0,66702672	0,800955536	0,43254686
        //11.04.2014	0,670182476	0,278783766	0,752237268
        //12.04.2014	0,337505115	0,405217108	0,514713717

        // 			    |	Алексей		|	Константин	|	Пётр
        //10.04.2014	|	0,66702672	|	0,800955536	|	0,43254686
        //11.04.2014	|	0,670182476	|	0,278783766	|	0,752237268
        //12.04.2014	|	0,337505115	|	0,405217108	|	0,514713717

        HashMap<Integer, String> columns = new HashMap<>();
        HashMap<Integer, String> rows = new HashMap<>();
        Object[][] objects = new Object[100][100];

        String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\txt_format\\format_2.txt";
        // построчное чтение
        try {
            String file = new String(Files.readAllBytes(Paths.get(path)));
            String[] lines = file.split("\n");
            String[] colNames = lines[0].split("\t");
            for (int i = 1; i < colNames.length; i++) columns.put(i, colNames[i]);

            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split("\t");
                rows.put(i-1, values[0]);
                for (int j = 1; j < values.length; j++)
                    objects[i-1][j-1] = values[j];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintWriter pw = new PrintWriter("F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\txt_format\\format_3.txt");

        // вывод
        String firstRow = "\t\t\t";
        for (Map.Entry entry : columns.entrySet()){
            String data = formatedString(entry.getValue().toString().trim());
            firstRow += "|\t" + data + "\t";
        }
        pw.write(firstRow);
        pw.flush();
        System.out.println(firstRow);
        int rowCount = 0;
        for (Map.Entry entry : rows.entrySet()){
            int columnCount = 0;
            String row = entry.getValue().toString() + "\t";
            for (Map.Entry entry_ : columns.entrySet()){
                String data = formatedString(objects[rowCount][columnCount].toString().trim());
                row += "|\t" + data + "\t";
                columnCount++;
            }
            pw.write("\n");
            pw.write(row);
            pw.flush();
            System.out.println(row);
            rowCount++;
        }
        pw.flush(); // сброс буферизации
        pw.close();
    }

    private static int formatLength = 12;
    public static String formatedString(String text){
        if (formatLength - text.length() < 0) return text;
        while (formatLength > text.length()-1)
            text += " ";
        return text;
    }
}
