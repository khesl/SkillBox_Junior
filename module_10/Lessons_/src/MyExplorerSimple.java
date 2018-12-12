package module_10.Lessons_.src;

import module_10.Lessons_.Lesson_add_simple;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=lLGoqvo-Dxo
 * */
public class MyExplorerSimple {
    private static Lesson_add_simple main = null;
    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel centerPanel;
    private JTextPane console;
    private JList filesList;
    private JScrollPane filesScroll;
    private JButton createFolderButton;
    private JButton deleteButton;
    private JButton renameButton;
    private JTextField pathField;
    private JButton backButton;
    private JPanel fileIconPanel;
    private JLabel fileIcon;
    private JList explorerList;
    private ArrayList<String> dirsCash = new ArrayList<>();

    DefaultListModel<File> model = new DefaultListModel<>();
    public MyExplorerSimple(Lesson_add_simple main){
        this.main = main;
        DefaultListModel model = new DefaultListModel();
        for (File file : File.listRoots())
            model.addElement(file);
        filesList.setModel(model);

        filesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 1) {
                    //if (dirsCash.size() > 1) {
                        String selectedObject = filesList.getSelectedValue().toString();
                        String fullPath = toFullPath(dirsCash);
                        final javax.swing.JFileChooser fc = new javax.swing.JFileChooser();
                        Icon icon = fc.getUI().getFileView(fc).getIcon(new File(fullPath + "\\"+ selectedObject));
                        //Icon icon = FileSystemView.getFileSystemView().getSystemIcon(new File(fullPath + "\\"+ selectedObject));
                        fileIcon.setIcon(icon);
                        getRootPanel().repaint();
                    //}
                }
                if (e.getClickCount() == 2) {
                    DefaultListModel model = new DefaultListModel();
                    String selectedObject = filesList.getSelectedValue().toString();
                    String fullPath = toFullPath(dirsCash);
                    File selectedFile;
                    System.out.println(fullPath + "|" + selectedObject);
                    if (dirsCash.size() > 1) {
                        selectedFile = new File(fullPath, selectedObject);
                    } else{
                        selectedFile = new File(fullPath + "\\"+ selectedObject);
                    }

                    if (selectedFile.isDirectory()){
                        String[] rootStr = selectedFile.list();
                        if (rootStr == null || rootStr.length == 0) {
                            JOptionPane.showMessageDialog(getRootPanel(), "Папка пуста.", "Ошибка", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }

                        for (String str : rootStr) {
                            File checkObject = new File (selectedFile.getPath(), str);
                            if (!checkObject.isHidden()) {
                                if (checkObject.isDirectory()) {
                                    model.addElement(str);
                                } else {
                                    model.addElement(" " + (char)9094 + " " + str);
                                }
                            }
                        }
                        dirsCash.add(selectedObject);
                        filesList.setModel(model);
                        pathField.setText(toFullPath(dirsCash));
                    }
                }
             }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dirsCash.size() > 1) {
                    dirsCash.remove(dirsCash.size() - 1);

                    filesList.setModel(updateModel());
                }
            }
        });
        createFolderButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!dirsCash.isEmpty()){
                    String newName = JOptionPane.showInputDialog(getRootPanel(), "Введите название папки", "Создание папки", JOptionPane.INFORMATION_MESSAGE);
                    String currentPath = toFullPath(dirsCash);
                    File newFolder = new File(currentPath, newName);
                    if (!newFolder.exists())
                        newFolder.mkdir();

                    filesList.setModel(updateModel());
                }
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (filesList.getSelectedIndex() < 0) return;
                if(!dirsCash.isEmpty()){

                    String selectedObject = filesList.getSelectedValue().toString();
                    String currentPath = toFullPath(dirsCash);
                    File file = new File(currentPath, selectedObject);

                    int disition = JOptionPane.showConfirmDialog(getRootPanel(), "Вы уверены что хотите удалить файл\n" + currentPath + selectedObject + "?", "Удалить", JOptionPane.WARNING_MESSAGE);
                    System.out.println(disition);
                    if (disition == 0) {
                        deleteDir(file);
                    }

                    filesList.setModel(updateModel());

                }
            }
        });
        renameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!dirsCash.isEmpty()){
                    String selectedName = filesList.getSelectedValue().toString();
                    String newName = JOptionPane.showInputDialog(getRootPanel(), "Введите новое название", "Новое имя", JOptionPane.INFORMATION_MESSAGE);
                    String currentPath = toFullPath(dirsCash);
                    File newFolder = new File(currentPath, selectedName);

                    newFolder.renameTo(new File(currentPath, newName));

                    filesList.setModel(updateModel());
                }
            }
        });
    }

    private DefaultListModel updateModel(){
        String backDir = toFullPath(dirsCash);
        String[] objects = new File(backDir).list();
        DefaultListModel model = new DefaultListModel();
        for (String str: objects){
            File checkFile = new File(backDir, str);
            if (!checkFile.isHidden()) {
                if (checkFile.isDirectory()) {
                    model.addElement(str);
                } else {
                    model.addElement(" " + (char)9094 + " " + str);
                }
            }
        }
        return model;
    }

    private String toFullPath(List<String> file){
        String listPart = "";
        for (String str : file)
            listPart += str + "\\";
        return listPart;
    }

    private void deleteDir(File file){
        File[] files = file.listFiles();
        if (files != null){
            for (File f: files){
                deleteDir(f);
            }
        }
        file.delete();
    }

    public class MyFile extends File{

        public MyFile(String pathname) {
            super(pathname);
        }

        @Override
        public String toString() {
            //super.toString();
            return (this.isDirectory()?(char)9112 :"  ") +  this.getName() + "\t\t" + this.length() + " Кб";
        }

    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JTextPane getConsole() {return console; }

    private void createUIComponents() {
        // TODO: place custom component creation code here

       // filesScroll = new JScrollPane(filesList);

    }
}
