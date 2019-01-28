package module_12.Lessons_.src;

import module_12.Lessons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class SiteMapBuilderGUI {
    private static Lessons main = null;
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JTextField SearchTextField;
    private JButton startButton;
    private JButton stopButton;
    private JButton fileToSaveButton;
    private JTextField chooseFileToSaveTextField;
    private JTextArea ConsoleTextArea;
    private JTextArea StatusTextArea;
    private JPanel ThreadsPanel;
    private JComboBox threadCountComboBox;

    private MyHttpScannerController mhsc;
    private String searchPath;

    public SiteMapBuilderGUI(Lessons main){
        this.main = main;

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SearchTextField.getText().length() == 0) return;
                searchPath = SearchTextField.getText();
                mhsc = new MyHttpScannerController(new TreeLinks(0, searchPath, searchPath));
                System.out.println(threadCountComboBox.getModel().getSelectedItem().toString());
                mhsc.setThreads_count(Integer.valueOf(threadCountComboBox.getModel().getSelectedItem().toString()));
                threadCountComboBox.setEditable(false);

                ThreadUpdater updater = new ThreadUpdater();
                updater.start();
                mhsc.start();

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
                // кнопка паузы
                mhsc.getLoader().setPause(!mhsc.getLoader().isPause());
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

    public class ThreadUpdater extends Thread {

        @Override
        public void run() {
            while (!interrupted()) {
                if (mhsc != null) {
                    if (!mhsc.getLoader().isPause()) {
                        //System.out.println("update visio.");
                        StringBuilder status = new StringBuilder();
                        for (MyHttpScanner_2 scanner : mhsc.getScanners()) {
                            status.append(scanner.getStatus()).append("\n");
                        }
                        StatusTextArea.setText(status.toString());

                        if (mhsc.isUpdateLinks()) {
                            ConsoleTextArea.setText(mhsc.getUniqueLinks().get(searchPath).toAllString());

                        }
                    }
                }
                try {
                    sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void writeToFile(String path, String text){}

    public JPanel getRootPanel(){ return rootPanel; }
}
