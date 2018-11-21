import javax.swing.*;

public interface Form {
    JPanel getRootPanel();
    String[] getFIO();
    void setFIO(String[] fio);
}
