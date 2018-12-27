package module_13.Lessons_.res;

import Utils.ConsoleColor;
import module_13.Lessons_.Lesson_3;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.ByteBuffer;

public class MyFileEditor {
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField filePathField;
    private JButton chooseFileButton;
    private JPanel bottomPanel;
    private JTextArea textArea;
    private JScrollBar scrollBarLocal;
    private JScrollBar scrollBarGlobal;
    private Lesson_3 lesson_3;
    private int oldScrollValue = 0;

    private boolean updateText = false;
    private MyWrapperFile myWrapperFile;
    private boolean mouseClicked = false;

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
                filePathField.setText(fileOpen.getSelectedFile().getAbsolutePath());
                scrollBarLocal.setMaximum((int)myWrapperFile.getFileLength());
                updateTextArea();
                textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(0));
                scrollBarGlobal.setMaximum((int)myWrapperFile.getFileLength());
                //scrollBarGlobal.setValue((int)myWrapperFile.getCurrentPosition());
                scrollBarLocal.setValue(0);
            }
        });
        /** управление локальным скролом*/
        scrollBarLocal.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.print(ConsoleColor.setColor("Local :", ConsoleColor.ANSI_YELLOW) + e.getValue() + "|"
                        + e.getValueIsAdjusting() + "|mouse(" + isMouseClicked() + ")" + "|" // true - если двигаем мышью, false - если тыкаем на стрелки
                        + e.getAdjustable().getMinimum() + ":" + e.getAdjustable().getMaximum());

                int percent = e.getValue() * 100 /
                        ((scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount())>0?(scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()):1);
                if (!e.getValueIsAdjusting() && isMouseClicked()){
                    /** попытка поймать состояние при приближении к границам локального буфера. не работает нормально,
                     * из-за дополнительного (неконтролируемого, фиг знает как отлавливаемого)
                     * вызова функции при обновлении текстового поля */
                    /*if (percent < 5) {
                        myWrapperFile.updateBuffer(scrollBarGlobal.getValue() - scrollBarLocal.getMaximum());
                        //textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                        textArea.setText(new String((byte[])myWrapperFile.getBuffer().array()).trim());
                    }
                    if (percent > 95) {
                        myWrapperFile.updateBuffer(scrollBarGlobal.getValue() + scrollBarLocal.getMaximum());
                        //textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                        textArea.setText(new String((byte[])myWrapperFile.getBuffer().array()).trim());
                    }*/
                }

                // полный ход scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()
                System.out.println(", visible: " + scrollBarLocal.getVisibleAmount() + ", (" + percent + ")%");

                if (!myWrapperFile.isHasFile()) return;
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

                if (isUpdateText()) {
                    int desition = JOptionPane.showConfirmDialog(getRootPanel(), "Текст был изменён. сохранить изменения?", "", JOptionPane.YES_NO_OPTION);
                    /**
                     * 0 - yes
                     * 1 - no
                     * 2 - cancel
                     * */
                    if (desition == JOptionPane.YES_OPTION) {
                        // сохранить новый буфер
                        ByteBuffer readBuffer = ByteBuffer.allocate(textArea.getText().length());
                        myWrapperFile.writeToFile(readBuffer.wrap(textArea.getText().getBytes()));
                        updateText = false;
                    } else { updateText = false; }
                }
                myWrapperFile.updateBuffer(e.getValue());
                //textArea.setText(myWrapperFile.getBufferedDataByGlobalPercent(percent));
                textArea.setText(new String((byte[])myWrapperFile.getBuffer().array()).trim());
                int scrolValue = 50 * ((scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount())>0?(scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()):1) / 100;

                //scrollBarLocal.setValue(scrolValue);
                //scrollBarLocal.setValue(scrolValue);
                //scrollBarGlobal.setValue((int)myWrapperFile.getCurrentPosition());

                oldScrollValue = e.getValue();
            }
        });
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                /** не нашел как записывать огромные файлы.. везде java предоставляет только возможность
                 * перезаписи всего файла, а не фрагмента */
                //updateText = true; // если флаг true, то включена запись
            }
        });
        scrollBarLocal.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //super.mousePressed(e);
                mouseClicked = true;
            }
        });
        scrollBarLocal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //super.mouseReleased(e);
                mouseClicked = false;
            }
        });
        textArea.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.print(evt.getPropertyName());
                if (evt.getPropertyName().equals("text")) {
                    System.out.println(" | change text");
                    int scrolValue = 50 * ((scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount())>0?(scrollBarLocal.getMaximum() - scrollBarLocal.getVisibleAmount()):1) / 100;
                    scrollBarLocal.setValue(scrolValue);
                }
            }
        });
    }

    private void updateTextArea(){

    }

    public boolean isUpdateText() {
        return updateText;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
