package module_12.Lessons_.src;

import module_12.Lessons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class SiteMapBuilderGUI {
    private static Lessons main = null;
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JTextField textField1;
    private JButton startButton;
    private JButton stopButton;
    private JButton fileToSaveButton;
    private JTextField chooseFileToSaveTextField;
    private JTree treesLink;


    public SiteMapBuilderGUI(Lessons main){
        this.main = main;


        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*if (!stopWatchClass.isStarted()) {
                    stopWatchClass.startStopWatch();
                    startButton.setText("pause");
                } else {
                    if (stopWatchClass.isPause()){
                        // сейчас на паузе
                        stopWatchClass.pause();
                        startButton.setText("pause");
                    } else {
                        // должно идти время
                        stopWatchClass.pause();
                        startButton.setText("play");
                    }
                }*/
            }
        });
        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*if (stopWatchClass.isStarted()){
                    stopWatchClass.stopStopWatchTime();
                    startButton.setText("start");
                }*/
            }
        });
        fileToSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int ret = fileopen.showDialog(null, "Выбрать файл");
                System.out.println(fileopen.getSelectedFile());
                File chooseFile = fileopen.getSelectedFile();

            }
        });

    }

    public JPanel getRootPanel(){ return rootPanel; }
}
