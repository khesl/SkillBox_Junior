package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainForm {
    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JList contactsList;
    private JButton updateContactButton;
    private JPanel leftWorkPanel;
    private JPanel rightWorkPanel;
    private JPanel topWorkPanel;
    private JTextField searchTextField;
    private JPanel searchPanel;
    private JPanel contactListPanel;
    private JPanel addContact;
    private JPanel excessPanel;
    private JPanel chatInfoBar;
    private JLabel currentContactLabel;
    private JPanel editContactButton;
    private JPanel writeMessagePanel;
    private JPanel messageSpacePanel;
    private JTextField messField;
    private JLabel settingsIcon;
    private JPanel settingsPanel;

    public MainForm(Main_09_New main) {
        this.main = main;
        BorderBarForm borderBarForm = new BorderBarForm(this.main);
        rootPanel.add(borderBarForm.getRootPanel(), BorderLayout.NORTH);

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
                currentContactLabel.setText(currentContact.getFirstName() + " " + currentContact.getLastName() + " " + currentContact.getPhone());
                //UserContact.createTLInputUser() // мне нужен этот метод!
                // либо с этим API нельзя достать данные, либо нужно дальше читать коды, чтобы понять как вызвать.
                //      а пока что, так и быть буду без аватарок контактов.
                /*chooseNumberField.setText(currentContactLabel.getPhone());
                TLUserContact TLUser = lessons.getDiplomApp().getTelegramApiBridge()... currentContactLabel.getId();
                TLUserProfilePhoto TLUserPhoto = (TLUserProfilePhoto)TLUser.getPhoto();
                TLUserPhoto.getPhotoSmall().getLocalId()
                        (TLUserProfilePhoto) currentContactLabel.getPhoto();
                lessons.getDiplomApp().getTelegramApiBridge().getPhoto(lessons.getDiplomApp().getTelegramApiBridge(), profilePhoto, true);
                */
            }
        });
        editContactButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //изменение нашего контакта.
                main.switchContentPanes(Main_09_New.ContentPanes.editContacts);
            }
        });
        addContact.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                // добавление контакта.
                main.switchContentPanes(Main_09_New.ContentPanes.addContacts);
            }
        });
        settingsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                // изменение данных профиля
                main.switchContentPanes(Main_09_New.ContentPanes.editProfile);
            }
        });
    }

    public JList getContactsList() {
        return contactsList;
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
