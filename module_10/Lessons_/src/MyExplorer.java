package module_10.Lessons_.src;

import module_10.Lessons_.Lesson_add;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyExplorer {
    private static Lesson_add main = null;
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JTextField consoleWrite;
    private JButton enterButton;
    private JList explorerList;
    private JButton selectPathButton;
    private JTextPane console;
    private JTextField pathField;
    private JButton button1;
    private JButton button2;

    public MyExplorer(Lesson_add main){
        this.main = main;
        explorerList.setModel(model);

        consoleWrite.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                switch (keyCode){
                    case KeyEvent.VK_UP:
                        if (explorerList.getSelectedIndex() > 0) {
                            //explorerList.setSelectedIndex(explorerList.getSelectedIndex() - 1);

                            int nextIndex = explorerList.getSelectedIndex();
                            System.out.println(nextIndex);
                            System.out.println(explorerList.getSelectedIndex() + " deep " + model.get(nextIndex).getDeep());
                            int newNextIndex = nextIndex;
                            while (newNextIndex > 0){
                                if (!model.get(newNextIndex).getFile().getPath().equals(model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex()).getFile().getPath())){
                                    System.out.println("newDeep: " + model.get(newNextIndex).getDeep() + "\t(need :" + main.getCurFile().getDeep() + ") " + model.get(newNextIndex).getFile().getName());
                                    System.out.println("\tnewNextIndex: " + newNextIndex);
                                    if (model.get(newNextIndex).getDeep() == main.getCurFile().getDeep()+1) break;
                                }
                                newNextIndex--;
                            }
                            if (newNextIndex < model.size()) nextIndex = newNextIndex;
                            System.out.println("new index: " + nextIndex);
                            explorerList.setSelectedIndex(nextIndex);
                        }
                        System.out.println("key " + (char)11105);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (explorerList.getSelectedIndex() < model.size()) {
                            int nextIndex = explorerList.getSelectedIndex();
                            if (nextIndex < 0) nextIndex = 1;
                            System.out.println(nextIndex);
                            System.out.println(explorerList.getSelectedIndex() + " deep " + model.get(nextIndex).getDeep());
                            int newNextIndex = nextIndex;
                            while (newNextIndex < model.size()){
                                if (!model.get(newNextIndex).getFile().getPath().equals(model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex()).getFile().getPath())){
                                    System.out.println("newDeep: " + model.get(newNextIndex).getDeep() + "\t(need :" + main.getCurFile().getDeep() + ") " + model.get(newNextIndex).getFile().getName());
                                    System.out.println("\tnewNextIndex: " + newNextIndex);
                                    if (model.get(newNextIndex).getDeep() == main.getCurFile().getDeep()+1) break;
                                }
                                newNextIndex++;
                            }
                            if (newNextIndex < model.size()) nextIndex = newNextIndex;
                            System.out.println("new index: " + nextIndex);
                            explorerList.setSelectedIndex(nextIndex);
                        }
                        System.out.println("key " + (char)11107);
                    break;
                    case KeyEvent.VK_ENTER:
                        String command = consoleWrite.getText().replaceAll("[#* ]", "");

                        if (command.equals(Lesson_add.ExplorerCommand.in.toString())) main.setCommand((Lesson_add.ExplorerCommand.in));
                        if (command.equals(Lesson_add.ExplorerCommand.out.toString())) main.setCommand((Lesson_add.ExplorerCommand.out));
                        if (command.equals(Lesson_add.ExplorerCommand.help.toString())) main.setCommand((Lesson_add.ExplorerCommand.help));
                        consoleWrite.setText("#");
                        System.out.println("key Enter");
                        break;
                }
            }
        });
        selectPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int ret = fileopen.showDialog(null, "Открыть файл");
                System.out.println(fileopen.getSelectedFile());
                main.setCurFile(new ExplorerObject(fileopen.getSelectedFile(), 0, null));
                main.updateFiles();
                updateFiles();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                main.setCommand((Lesson_add.ExplorerCommand.in));
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                main.setCommand((Lesson_add.ExplorerCommand.out));
            }
        });
    }


    DefaultListModel<ExplorerObject> model = new DefaultListModel<>();
    public void updateFiles(){
        model.clear();
        for (ExplorerObject contact : main.getFiles())
            model.addElement(contact);
        pathField.setText(main.getCurFile().getFile().getPath());
        explorerList.ensureIndexIsVisible(explorerList.getSelectedIndex());
    }
    public void deepToFiles(){
        /* МЕНЯТЬ! тут нужно не подбор, а переход к внутреннему файлу...*/
        int nextIndex = explorerList.getSelectedIndex();
        System.out.println("curIndex " + nextIndex);
        if (nextIndex < 0) nextIndex = 0;
        System.out.println(explorerList.getSelectedIndex() + " deep " + model.get(nextIndex).getDeep());
        while (model.get(nextIndex).getDeep() != model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex()).getDeep() + 1) {
            nextIndex++;
            System.out.println(nextIndex);
        }
        System.out.println("newIndex " + nextIndex);
        explorerList.setSelectedIndex(nextIndex);

    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JTextPane getConsole() {return console; }
    public JList getExplorerList() { return explorerList; }
    public ExplorerObject getSelectedObject(){
        return model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex());
    }
    public ExplorerObject getNextObject(){
        return model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex() + 1);
    }
    public ExplorerObject getPrevObject(){
        return model.get(explorerList.getSelectedIndex()<0?0:explorerList.getSelectedIndex()).getParent();
    }
}
