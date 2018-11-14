package module_6;

import Utils.ConsoleColor;
import module_6.src.src_Lesson_5.Bird;
import module_6.src.src_Lesson_5.BirdComparator;
import module_6.src.src_Lesson_5.Objects.*;
import module_6.src.src_Lesson_5.Vertebrate;
import module_6.src.src_Lesson_6.Chair;
import module_6.src.src_Lesson_6.Furniture;
import module_6.src.src_Lesson_6.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lessons {


    public static void main(String[] args) {

        // - по 4 уроку задания перекликаются далее, в рамках Гит все изменения обновлены,
        // ведь не нужно делать отдельно то, что используется далее?

        //Lesson_5();
        //Lesson_6_1();
        Lesson_6_2();
    }

    private static void Lesson_5(){
        //- Добавить генерацию случайного веса у животного в конструктор и сделать метод voice(),
        // который печатает голос животного (например, “кря-кря”) и его вес.
        //
        //- Сделать всех животных сравниваемыми по весу.
        //
        //- Создать много животных, отсортировать по весу и вызвать у всех метод voice().

        List<Bird> birds = new ArrayList<>();
        birds.add(new Sparrow());
        birds.add(new Duck());
        birds.add(new Duck());
        birds.add(new Duck());
        birds.add(new Sparrow());
        birds.add(new Ostrich());
        birds.add(new Sparrow());
        birds.add(new Chicken());

        Collections.sort(birds, new BirdComparator());

        for (Vertebrate vertebrate : birds){
            vertebrate.description();
            vertebrate.voice();
        }
    }

    /**
     * как смог, без конкретного описания методов и классов, хотябы поверхостного описания используемых объектов
     * или анализа приложения.
    */
    private static void Lesson_6_1(){
        //- Перечислить сущности, которые будут в вашем дипломном приложении, со свойствами и методами. Примеры: Contact, Message...
        /**
         В примерах дизайна 7 файлов, попробую описать сущности по ним.
         1 - ввод номера телефона
         2 - ввод кода подтверждения
         3 - регистрация
         4 - основной экран приложения
         5 - настройки профиля
         6 - добавление контакта
         7 - редактирование контакта

         TelegramAPP    - сущность приложение
         > Accaunts[]   - сущность аккаунты (их ведь может быть несколько)
            > AuthData      - сущность для авторизации на примере @link org.javagram.response.AuthAuthorization
                > @String phoneNum          //не знаю нужно ли делать данный класс, для того чтобы провести авторизацию пользователя,
                > @int smsCode              // и хранить какие-то данные, вроде Хэша кода авторизации, для повторного входа без кода.
                > @boolean statusReg        - для актуальных параметров, статус регистрации
                > @boolean statusAuth       - статус авторизации
            > User          - Объект (сущность) пользователь.
                > @String name              - Имя
                > @String surname           - Фамилия
                > @String id (nickName)     - Id или никНейм
                > @Image icon               - данные иконки
            > Chats[]        - Коллекция сущностей Чаты
                > contact[] (?)         - содержат список приглашённых пользователей (сущность Contact)
                    > @User user            - информация о пользователе
                    > @String phoneNum      - контактная информация
                    > @boolean isOnline     - информация об онлайне контакта
                > metaData[] (?)        - какие-то другие данные чата
                > @int chatId           - идентификатор чата
                > message[]             - контейнер сообщений чата
                    > @String text          - собственно текст сообщения
                    > @String senderId      - идентификатор отправителя
                    > @Date sentDate        - дата отправки
                    > @int destinationChatId - возможно нужно знать адрес чата назначения

         Методы (помимо геттеров/сеттеров)
         сущность Accaunts ->
            refreshData()
            signIn()
            signUp()
            logOut()
         сущность AuthData ->
            getSmsCode()
            getTextCode()
         сущность User     ->
            -
         сущность Chats    ->
            getUsers()
            setMessage()
            removeMessage()
            addUser()
            removeUser()
            editMessage()
            gesMessages()
         сущность contact  ->
            setData()
            remove()
            searchContact()
            addContact()
         сущность message  ->
            getData()
            isEditable()
            setData()

         Помимо этого нужны объекты GUI, думаю их описание тут не нужно?
         * */
        System.out.println("В примерах дизайна 7 файлов, попробую описать сущности по ним.\n" +
                "         1 - ввод номера телефона\n" +
                "         2 - ввод кода подтверждения\n" +
                "         3 - регистрация\n" +
                "         4 - основной экран приложения\n" +
                "         5 - настройки профиля\n" +
                "         6 - добавление контакта\n" +
                "         7 - редактирование контакта\n" +
                "\n" +
                "         TelegramAPP    - сущность приложение\n" +
                "         > Accaunts[]   - сущность аккаунты (их ведь может быть несколько)\n" +
                "            > AuthData      - сущность для авторизации на примере @link org.javagram.response.AuthAuthorization\n" +
                "                > @String phoneNum          //не знаю нужно ли делать данный класс, для того чтобы провести авторизацию пользователя,\n" +
                "                > @int smsCode              // и хранить какие-то данные, вроде Хэша кода авторизации, для повторного входа без кода.\n" +
                "                > @boolean statusReg        - для актуальных параметров, статус регистрации\n" +
                "                > @boolean statusAuth       - статус авторизации\n" +
                "            > User          - Объект (сущность) пользователь.\n" +
                "                > @String name              - Имя\n" +
                "                > @String surname           - Фамилия\n" +
                "                > @String id (nickName)     - Id или никНейм\n" +
                "                > @Image icon               - данные иконки\n" +
                "            > Chats[]        - Коллекция сущностей Чаты\n" +
                "                > contact[] (?)         - содержат список приглашённых пользователей (сущность Contact)\n" +
                "                    > @User user            - информация о пользователе\n" +
                "                    > @String phoneNum      - контактная информация\n" +
                "                    > @boolean isOnline     - информация об онлайне контакта\n" +
                "                > metaData[] (?)        - какие-то другие данные чата\n" +
                "                > @int chatId           - идентификатор чата\n" +
                "                > message[]             - контейнер сообщений чата\n" +
                "                    > @String text          - собственно текст сообщения\n" +
                "                    > @String senderId      - идентификатор отправителя\n" +
                "                    > @Date sentDate        - дата отправки\n" +
                "                    > @int destinationChatId - возможно нужно знать адрес чата назначения\n" +
                "\n" +
                "         Методы (помимо геттеров/сеттеров)\n" +
                "         сущность Accaunts ->\n" +
                "            refreshData()\n" +
                "            signIn()\n" +
                "            signUp()\n" +
                "            logOut()\n" +
                "         сущность AuthData ->\n" +
                "            getSmsCode()\n" +
                "            getTextCode()\n" +
                "         сущность User     ->\n" +
                "            -\n" +
                "         сущность Chats    ->\n" +
                "            getUsers()\n" +
                "            setMessage()\n" +
                "            removeMessage()\n" +
                "            addUser()\n" +
                "            removeUser()\n" +
                "            editMessage()\n" +
                "            gesMessages()\n" +
                "         сущность contact  ->\n" +
                "            setData()\n" +
                "            remove()\n" +
                "            searchContact()\n" +
                "            addContact()\n" +
                "         сущность message  ->\n" +
                "            getData()\n" +
                "            isEditable()\n" +
                "            setData()\n" +
                "         \n" +
                "         Помимо этого нужны объекты GUI, думаю их описание тут не нужно?");
    }

     /**
     * Пример наследования классов их методов и переменных
     * */
    private static void Lesson_6_2(){
        //- Создать классы “Стул”, “стол” и “мебель”, задайте им методы и правильно отнаследуйте эти классы друг от друга.
        Furniture chair = new Chair(40d, 40d, 80d, 3d, 5, "Oak Wood", 3);
        Furniture table = new Table(40d, 40d, 80d, 3d, 4, "Oak Wood", 6);
        Furniture chair_2 = new Chair(40d, 40d, 80d, 3d, 4, "Oak Wood", 2);
        Furniture table_2 = new Table(40d, 40d, 80d, 3d, 5, "Oak Wood", 8);

        System.out.println("Из одного ли комплекта стол и стул1: " + (table.isComplect(chair) ? "да" : "нет"));
        System.out.println("Из одного ли комплекта стол и стул2: " + (table.isComplect(chair_2) ? "да" : "нет"));

        List<Furniture> furnitures = new ArrayList<>();
        furnitures.add(table);
        furnitures.add(chair);
        furnitures.add(chair);
        furnitures.add(chair_2);
        furnitures.add(chair_2);
        furnitures.add(table_2);
        furnitures.add(chair_2);

        for (Furniture furniture : furnitures)
            if (furniture.getClass().getName().equals(Table.class.getName())){
                System.out.println("Полный ли комплект для Стола: " + furniture.getDescription());
                System.out.println(ConsoleColor.setColor(Furniture.isFullComplect(furnitures, (Table)furniture) ? "\tда" : "\tнет", ConsoleColor.Color.ANSI_BLUE));
        }

    }
}
