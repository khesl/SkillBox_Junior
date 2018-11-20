package Diploma;

import Utils.ConsoleColor;
import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.AuthSentCode;
import org.javagram.response.object.UserContact;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DiplomaProj {
    private static final String hostAddr = "149.154.167.50:443";
    private static final int appApiId = 404903;
    private static final String appHash = "f8af7088e538d31e99e5bcf7d2a7d66b";
    private static Scanner  in;

    private static AuthAuthorization authAuthorization;
    private static TelegramApiBridge telegramApiBridge;
    private static AuthCheckedPhone authCheckedPhone;
    private String phone;
    private boolean setPhone = false;
    private boolean authorized = false;

    public DiplomaProj(){}

    public static void main(String[] args) throws IOException {
        DiplomaProj proj = new DiplomaProj();
        in = new Scanner(System.in);
        //TelegramApi tab = new TelegramApi();

        proj.setPhone("+77771652384");
        proj.getSmsAuthorisation();

        String code = "";
        while (code.length() < 4 ){
            System.out.print(ConsoleColor.setColor("Write your sms code: ", ConsoleColor.ANSI_YELLOW));
            code = String.valueOf(in.nextLine()).trim();
        }
        proj.authorized(code);

        proj.getContacts();

    }

    /**
     *  Заполняем двуфакторную авторизацию с API Telegram.
     * */
    public boolean getSmsAuthorisation() throws IOException {
        if (!isSetPhone()) throw new NullPointerException("Need to set phone");

        telegramApiBridge = new TelegramApiBridge(hostAddr, appApiId, appHash);
        // String phone = "";
        /*while (phone.length() < 11 ){
            System.out.print(ConsoleColor.setColor("Write your phone number: ", ConsoleColor.ANSI_YELLOW));
            phone = String.valueOf(in.nextLine()).trim();
        }*/
        authCheckedPhone = telegramApiBridge.authCheckPhone(phone);
        System.out.println(ConsoleColor.setColor("isInvited(" + authCheckedPhone.isInvited() + "); isRegistered("
                + authCheckedPhone.isRegistered() + ")", ConsoleColor.ANSI_RED));
        AuthSentCode asc = telegramApiBridge.authSendCode(phone);
        String hash = asc.getPhoneCodeHash();
        return true;
    }
    public boolean authorized(String code) throws IOException {
        if (telegramApiBridge == null) return false;
        authAuthorization = telegramApiBridge.authSignIn(code);
        authorized = true;
        return true;
    }

    /**
     * вывод списка контактов
     * */
    List<UserContact> contacts = null;
    public List<UserContact> getContacts() throws IOException {
        if (contacts != null) return contacts;
        if (!isAuthorized()) throw new NullPointerException("Need Authorized first.");
        contacts = telegramApiBridge.contactsGetContacts();
        return contacts;
    }
    public List<UserContact> updateContacts() throws IOException {
        if (!isAuthorized()) throw new NullPointerException("Need Authorized first.");
        contacts = telegramApiBridge.contactsGetContacts();
        return contacts;
    }



    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; setPhone = true;}

    public boolean isSetPhone() { return setPhone; }
    public boolean isAuthorized() { return authorized; }

    public AuthAuthorization getAuthAuthorization() { return authAuthorization; }
    public TelegramApiBridge getTelegramApiBridge() { return telegramApiBridge; }
    public AuthCheckedPhone getAuthCheckedPhone() { return authCheckedPhone; }

}
