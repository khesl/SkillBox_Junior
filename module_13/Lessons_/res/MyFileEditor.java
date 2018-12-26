package module_13.Lessons_.res;

import Utils.ConsoleColor;
import Utils.MyUtils;
import module_13.Lessons_.Lesson_3;

import javax.swing.*;
import java.awt.event.*;

public class MyFileEditor {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField FilePathField;
    private JButton chooseFileButton;
    private JPanel bottomPanel;
    private JTextArea textArea;
    private JScrollBar scrollBarLocal;
    private JScrollBar scrollBarGlobal;
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
                scrollBarLocal.setMaximum((int)myWrapperFile.getFileLength());
                updateTextArea();
                textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(0));
                scrollBarGlobal.setMaximum((int)myWrapperFile.getFileLength());
                scrollBarGlobal.setValue((int)myWrapperFile.getCurrentPosition());
                scrollBarLocal.setValue(0);
            }
        });
        /** управление локальным скролом*/
        scrollBarLocal.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.print(ConsoleColor.setColor("Local :", ConsoleColor.ANSI_YELLOW) + e.getValue() + "|"
                        + e.getValueIsAdjusting() + "|" // true - если двигаем мышью, false - если тыкаем на стрелки
                        + e.getAdjustable().getMinimum() + ":" + e.getAdjustable().getMaximum());

                int percent = e.getValue() * 100 /
                        ((scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount())>0?(scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()):1);

                // полный ход scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()
                System.out.println(", visible: " + scrollBarLocal.getVisibleAmount() + ", (" + percent + ")%");

                if (!myWrapperFile.isHasFile()) return;
                // e.getValueIsAdjusting() // кнопки или бегунок
                    // глобальное чтение
                    //textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                    //scrollBarGlobal.setValue((int)myWrapperFile.getCurrentPosition());
                // локальное чтение
                long currentPos = myWrapperFile.getCurrentPosition();

                if (e.getValue() > oldScrollValue || (e.getValue() == 0 && oldScrollValue == 0)){
                    if (scrollBarGlobal.getValue() < scrollBarGlobal.getMaximum()) {
                        //scrollBarGlobal.setValue(scrollBarGlobal.getValue() + 1);
                    }
                }
                else{
                    if (scrollBarGlobal.getValue() > 0){
                        //scrollBarGlobal.setValue(scrollBarGlobal.getValue() - 1);
                    }
                }
                oldScrollValue = e.getValue();
            }
        });
        /** управление глобальным скролом*/
        scrollBarGlobal.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.print(ConsoleColor.setColor("Global:", ConsoleColor.ANSI_RED) + e.getValue() + "|"
                        + e.getValueIsAdjusting() + "|" // true - если двигаем мышью, false - если тыкаем на стрелки
                        + e.getAdjustable().getMinimum() + ":" + e.getAdjustable().getMaximum());
                //System.out.println();

                int percent = e.getValue() * 100 /
                        ((scrollBarGlobal.getMaximum() - scrollBarGlobal.getVisibleAmount())>0?(scrollBarGlobal.getMaximum() - scrollBarGlobal.getVisibleAmount()):1);
                System.out.println(", visible: " + scrollBarLocal.getVisibleAmount() + ", (" + percent + ")%");
                if (!myWrapperFile.isHasFile()) return;

                myWrapperFile.updateBuffer(e.getValue());

                textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                //scrollBarGlobal.setValue((int)myWrapperFile.getCurrentPosition());
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                /** TODO: будем смотреть если был ввод текста, или какое-то изменение, то нужно будет перед загрузкой нового буфера спросить
                 * нужно ли это сохранить, и после вставить текущий буфер в currentPosition позицию.
                 * если это получится, то почистить код.
                 */
            }
        });
    }

    private void updateTextArea(){

    }

    public JPanel getRootPanel(){ return rootPanel; }
}
