package module_8;

import org.javagram.response.object.UserContact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.IOException;

public class Form_7_2_3 {
    private static Lessons lessons = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JList contactsList;
    private JLabel labelChooseContactLabel;
    private JTextField chooseTextField;
    private JButton обновитьКонтактыButton;
    private JTextField chooseNumberField;

    public Form_7_2_3(Lessons lessons) {
        this.lessons = lessons;
        contactsList.setModel(model);

        обновитьКонтактыButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateContacts();
            }
        });
        contactsList.addComponentListener(new ComponentAdapter() {
        });
        contactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    System.out.println("first " + e.getFirstIndex()); // выбранный сейчас
                    System.out.println("last  " + e.getLastIndex()); // выбранный до этого
                    UserContact currentContact = lessons.getDiplomApp().getContacts().get(e.getFirstIndex());
                    chooseTextField.setText(currentContact.getFirstName() + " " + currentContact.getLastName());
                    chooseNumberField.setText(currentContact.getPhone());
                    //UserContact.createTLInputUser() // мне нужен этот метод!
                    // либо с этим API нельзя достать данные, либо нужно дальше читать коды, чтобы понять как вызвать.
                    //      а пока что, так и быть буду без аватарок контактов.
                    /*chooseNumberField.setText(currentContact.getPhone());
                    TLUserContact TLUser = lessons.getDiplomApp().getTelegramApiBridge()... currentContact.getId();
                    TLUserProfilePhoto TLUserPhoto = (TLUserProfilePhoto)TLUser.getPhoto();
                    TLUserPhoto.getPhotoSmall().getLocalId()
                            (TLUserProfilePhoto) currentContact.getPhoto();
                    lessons.getDiplomApp().getTelegramApiBridge().getPhoto(lessons.getDiplomApp().getTelegramApiBridge(), profilePhoto, true);
                    */
                } catch (IOException e1) {
                    chooseTextField.setText("---");
                    //e1.printStackTrace();
                }
            }
        });
    }

    DefaultListModel<String> model = new DefaultListModel<>();
    public void updateContacts(){
        try {
            model.clear();
            for (UserContact contact : lessons.getDiplomApp().getTelegramApiBridge().contactsGetContacts())
                model.addElement( contact.getFirstName() + " " + contact.getLastName());
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(getRootPanel(), "Произошла ошибка при обновлении контактов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    public JPanel getRootPanel(){ return rootPanel; }
}
