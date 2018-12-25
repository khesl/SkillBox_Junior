package module_13.Lessons_.res;

import module_13.Lessons_.Lesson_3;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class MyFileEditor {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField FilePathField;
    private JButton chooseFileButton;
    private JPanel bottomPanel;
    private JTextArea textArea;
    private JScrollBar scrollBar1;
    private Lesson_3 lesson_3;
    private int oldScrollValue = 0;

    private MyWrapperFile myWrapperFile;

    public MyFileEditor(Lesson_3 lesson_3){
        this.lesson_3 = lesson_3;
        myWrapperFile = new MyWrapperFile();


        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int ret = fileOpen.showDialog(null, "Открыть файл");
                System.out.println(fileOpen.getSelectedFile());
                myWrapperFile.setFile(fileOpen.getSelectedFile());
                scrollBar1.setMaximum((int)myWrapperFile.getFileLength());
                updateTextArea();
            }
        });
        scrollBar1.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.print(e.getValue() + "|");
                System.out.print(e.getValueIsAdjusting() + "|"); // true - если двигаем мышью, false - если тыкаем на стрелки
                System.out.print(e.getAdjustable().getMinimum() + ":" + e.getAdjustable().getMaximum() + ", source:" + e.getSource());

                int percent = e.getValue() * 100 /
                        ((scrollBar1.getMaximum() - scrollBar1.getVisibleAmount())>0?(scrollBar1.getMaximum() - scrollBar1.getVisibleAmount()):1);

                // полный ход scrollBar1.getMaximum() - scrollBar1.getVisibleAmount()
                System.out.println(", max: " + scrollBar1.getVisibleAmount() + ", (" + percent + ")%");

                if (!myWrapperFile.isHasFile()) return;
                if (e.getValueIsAdjusting()){
                    // глобальное чтение
                    try {
                        textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    // локальное чтение
                    long currentPos = myWrapperFile.getCurrentPosition();
                    try {
                        if (e.getValue() > oldScrollValue || (e.getValue() == 0 && oldScrollValue == 0))
                            textArea.setText(myWrapperFile.getBufferedDataByInc());
                        else
                            textArea.setText(myWrapperFile.getBufferedDataByDec());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                oldScrollValue = e.getValue();
                //scrollBar1.setMaximum((int)myWrapperFile.getFileLength());
                //scrollBar1.setValue((int)myWrapperFile.getCurrentPosition());
                //scrollBar1.setValue((int)(myWrapperFile.getCurrentPosition()*scrollBar1.getMaximum()/myWrapperFile.getFileLength()));
            }
        });
    }

    private void updateTextArea(){

    }

    public JPanel getRootPanel(){ return rootPanel; }
}
