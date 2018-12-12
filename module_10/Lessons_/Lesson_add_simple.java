package module_10.Lessons_;

import module_10.Lessons_.src.ExplorerObject;
import module_10.Lessons_.src.MyExplorerSimple;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lesson_add_simple {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException, ParseException {
        scanner = new Scanner(System.in);
        Lesson_add_simple main = new Lesson_add_simple();

        //deepIntoTheFile(new File("F:\\files\\java"));

        main.StartForm();
    }

    private JFrame frame;
    private MyExplorerSimple myExplorer;
    private void StartForm(){

        myExplorer = new MyExplorerSimple(this);
        frame = createMainFrame(myExplorer.getRootPanel(), "Проводник", new Dimension(640,480));
        //frame.setUndecorated(true); // убрать системные бордеры у окна!

        frame.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame.setVisible(true);
    }

    private static JFrame createMainFrame(JPanel panel, String formName, Dimension dimension){
        JFrame frame = new JFrame();

        frame.setContentPane(panel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width, dimension.height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(formName);

        return frame;
    }

    //-----------------------------------------------------------
    private static ExplorerObject curFile;
    private List<ExplorerObject> files = new ArrayList<>();

    public ExplorerObject getCurFile() { return curFile; }
    public void setCurFile(ExplorerObject curFile) { this.curFile = curFile; System.out.println("setCurFile: '" + curFile.getFile().getPath() + "' " + curFile.getDeep());}

    public List<ExplorerObject> getFiles() { return files; }
    public void setFiles(ArrayList<ExplorerObject> files) { this.files = files; }
    /*public void updateFiles() {
        files.clear();
        files = deepIntoTheFile(curFile);
        myExplorer.updateFiles();
        System.out.println("updateFiles() " + files.size());
    }*/
    public static List<ExplorerObject> deepIntoTheFile(ExplorerObject object){
        File file = object.getFile();
        List<ExplorerObject> files = new ArrayList<>();
        return deepIntoTheFile(files, file, 0, object.getParent());
    }
    private static List<ExplorerObject>  deepIntoTheFile(List<ExplorerObject> files, File file, int deep, ExplorerObject parent){
        ExplorerObject current;
        // добавим родительскую запись
        if (parent != null && deep == 0) {
            files.add(new ExplorerObject(parent.getFile(), deep, null));
            System.out.println(new ExplorerObject(parent.getFile(), deep, null));
            deep++;
        }
        if (deep > 2) {
            current = new ExplorerObject(file, deep, parent);
            files.add(current);
            System.out.println(current);
            return files; // ограничение по вложенности.
        }
        current = new ExplorerObject(file, deep, parent);
        files.add(current);
        System.out.println(current);
        for (File f : file.listFiles()){
            if (f.isDirectory())
                deepIntoTheFile(files, f, deep + 1, current);
            else {
                files.add(new ExplorerObject(f, deep + 1, current));
                System.out.println(new ExplorerObject(f, deep + 1, current));
            }
        }
        return files;
    }
    /** то как было
     *
     * private static List<ExplorerObject>  deepIntoTheFile(List<ExplorerObject> files, File file, int deep, ExplorerObject parent){
        ExplorerObject current;
        //String tab = "";
        //for (int i = 0; i < deep; i++) tab += "\t";
        // добавим родительскую запись
        if (parent != null && deep == 0) {
            //String str_ = "|" + (deep > 0 ? tab : "") + "  " + (char)9112 + " " + parent.getFile().getName();
            //tab += "\t";
            files.add(new ExplorerObject(parent.getFile(), deep, null));
            System.out.println(new ExplorerObject(parent.getFile(), deep, null));
        }
        //String str = "|" + (deep > 0 ? tab : "") + "  " + (char)9112 + " " + file.getName();
        if (deep > 1) {
            current = new ExplorerObject(file, deep, parent);
            files.add(current);
            System.out.println(current);
            return files; // ограничение по вложенности.
        }
        //str += " " + (char)11101;
        current = new ExplorerObject(file, deep, parent);
        files.add(current);
        System.out.println(current);
        //System.out.println(tab + file.getName() + ConsoleColor.setColor("\t\t" + file.length(), ConsoleColor.ANSI_BLUE));
        //str = tab + "\t  " + (char)10551;
        for (File f : file.listFiles()){
            if (f.isDirectory())
                deepIntoTheFile(files, f, deep + 1, current);
            else {
                //str = "|" + tab + "\t  " +"\t" + f.getName();
                files.add(new ExplorerObject(f, deep, current));
                System.out.println(new ExplorerObject(f, deep, current));
                //System.out.println(tab + "\t" + f.getName() + ConsoleColor.setColor("\t\t" + f.length(), ConsoleColor.ANSI_BLUE));
            }
        }
        return files;
    }*/

    /*public enum ExplorerCommand {
        in      ("in    - команда чтобы войти в директорию;"),
        out     ("out   - команда чтобы выйти на уровень выше;"),
        help    ("help  - посмотреть список команд.");

        ExplorerCommand(String description){
            this.description = description;
        }

        private String description;
        public String getDescription() { return description; }
    }*/
    /*public void setCommand(ExplorerCommand explorerCommand){
        switch (explorerCommand){
            case in:
                if (getCurFile() == null) break;
                if (!myExplorer.getSelectedObject().getFile().isDirectory()){
                    myExplorer.getConsole().setText(myExplorer.getConsole().getText() + "\nconsole# current file is not a directory.");
                    break;
                }
                myExplorer.deepToFiles();
                setCurFile(myExplorer.getSelectedObject());
                updateFiles();
                myExplorer.getConsole().setText(myExplorer.getConsole().getText() + "\nconsole# in command.");
                break;
            case out:
                if (getCurFile() == null) break;
                setCurFile(myExplorer.getPrevObject());
                updateFiles();
                myExplorer.getConsole().setText(myExplorer.getConsole().getText() + "\nconsole# out command.");
                break;
            case help:
                for (ExplorerCommand command : ExplorerCommand.values())
                    myExplorer.getConsole().setText(myExplorer.getConsole().getText() + "\n" + command.getDescription());
                break;
            default:
                myExplorer.getConsole().setText(myExplorer.getConsole().getText() + "\nconsole# wrong command.");
                break;
        }
    }*/
}
