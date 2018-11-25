package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainForm {
    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JList contactsList;
    private JLabel labelChooseContactLabel;
    private JTextField chooseTextField;
    private JButton updateContactButton;
    private JTextField chooseNumberField;

    public MainForm(Main_09_New main) {
        this.main = main;

        updateContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { main.updateContacts(); }
        });
        contactsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("first " + e.getFirstIndex()); // выбранный сейчас
                System.out.println("last  " + e.getLastIndex()); // выбранный до этого
                UserContact currentContact = main.getModel().get(e.getFirstIndex());
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
            }
        });
    }

    public JList getContactsList() {
        return contactsList;
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
