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
    private String phone;
    private static boolean setPhone = false;
    private static boolean authorized = false;

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
        AuthCheckedPhone acp = telegramApiBridge.authCheckPhone(phone);
        System.out.println(ConsoleColor.setColor("isInvited(" + acp.isInvited() + "); isRegistered("
                + acp.isRegistered() + ")", ConsoleColor.ANSI_RED));
        AuthSentCode asc = telegramApiBridge.authSendCode(phone);
        String hash = asc.getPhoneCodeHash();
        return true;
    }
    public boolean authorized(String code) throws IOException {
        authAuthorization = telegramApiBridge.authSignIn(code);
        authorized = true;
        return true;
    }

    /**
     * вывод списка контактов
     * */
    public static void getContacts() throws IOException {
        if (!isAuthorized()) throw new NullPointerException("Need Authorized first.");
            List<UserContact> contacts = telegramApiBridge.contactsGetContacts();
            for (UserContact contact : contacts)
                System.out.println(ConsoleColor.setColor(contact.getFirstName() + " "
                        + contact.getLastName() + " " + contact.getPhone(), ConsoleColor.ANSI_BLUE));

    }


    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; setPhone = true;}

    public static boolean isSetPhone() { return setPhone; }
    public static boolean isAuthorized() { return authorized; }

    public static AuthAuthorization getAuthAuthorization() { return authAuthorization; }
    public static TelegramApiBridge getTelegramApiBridge() { return telegramApiBridge; }

}
