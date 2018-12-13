package module_11.Lessons_;

import Utils.ConsoleColor;
import Utils.MyParametersController;
import module_11.res.Hibernate.src.objects.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.*;
import java.util.regex.Pattern;

/** создать интерфейс админки через которую можно добавять, удалять
 * и редактоировать данные любой таблицы на ваш выбор.*/
public class Lesson_add {
    private static Scanner scanner;
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Lesson_add la = new Lesson_add();

        la.Method();
    }

    private enum Command{
        tables  ("вывести список доступных таблиц;"),
        select  ("сделать выборку из таблицы;"),
        sudo    ("задать статус админа;"),
        whoami  ("узнать о текущем статусе;"),
        delete  ("удалить строку в таблице;"),
        update  ("обновить строку в таблице;"),
        set     ("добавить строку в таблицу;"),
        help    ("выводит список команд с их описанием;"),
        exit    ("выход из программы.");

        private String description;
        Command (String description){
            this.description = description;
        }

        public String getDescription(){
            return description;
        }
    }
    private enum Permission{
        user,
        admin
    }
    private enum Tables{
        vacation    (Vacation.class, "Vacation"),
        friendship  (Friendship.class, "Friendship"),
        employee    (Employee.class, "Employee"),
        department  (Department.class, "Department");

        private Class<?> classType;
        private String className;

        Tables(Class<?> classType, String className){
            this.classType = classType;
            this.className = className;
        }
        public Class<?> getClassType() {
            return classType;
        }
        public String getClassName() {
            return className;
        }
    }
    private Permission permit = Permission.user;
    public void Method(){
        /** 2 режима, обычный и админ.
         * в обычном доступно tables (все таблицы), select (вытащить из таблицы), sudo(переход в админку)
         * в админе помимо.. ещё delete, update, set
         *
         * предполагаю это будет консольное приложение как до этого, с расширенным командным набором.
         * */
        String in;
        System.out.println(ConsoleColor.setColor("robot# Hello, what do you want? (help, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
            Command command;
            try {
                command = Command.valueOf(in);
            } catch (Exception e){
                if (!in.equals("")) System.out.println(ConsoleColor.setColor("robot# Try to write new command. (help)", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                continue;
            }
            switch (command){
                case tables:{
                    for (Tables table: Tables.values()){
                        System.out.println(ConsoleColor.setColor("\t" + table.toString(), ConsoleColor.Color.ANSI_BLUE));
                    }
                    //System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    break;
                }
                case select:{
                    System.out.println(ConsoleColor.setColor("robot# Please choose the table:", ConsoleColor.Color.ANSI_YELLOW));
                    for (Tables table: Tables.values()){
                        System.out.println(ConsoleColor.setColor("\t" + table.toString(), ConsoleColor.Color.ANSI_BLUE));
                    }
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    try{
                        for (Beans beans : selectRow(Tables.valueOf(in = scanner.nextLine())))
                            System.out.println(ConsoleColor.setColor("\t" + beans.toString(), ConsoleColor.Color.ANSI_BLUE));
                    } catch(Exception e){
                        System.out.println(ConsoleColor.setColor("robot# That table does not exist.", ConsoleColor.Color.ANSI_YELLOW));
                        e.printStackTrace();
                        //System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    }
                    break;
                }
                case whoami: {
                    System.out.print(ConsoleColor.setColor("robot# Hello! Your permission is '", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor( permit.toString(), ConsoleColor.Color.ANSI_RED));
                    System.out.println(ConsoleColor.setColor("'.", ConsoleColor.Color.ANSI_YELLOW));
                    break;
                }
                case sudo: {
                    System.out.print(ConsoleColor.setColor("robot# Please, tell me your ", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor( "admin", ConsoleColor.Color.ANSI_RED));
                    System.out.println(ConsoleColor.setColor(" password. Nobody will know that. (root)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    try {
                        if ((in = scanner.nextLine()).equals(MyParametersController.getProperties("db_rot_pass"))) {
                            permit = Permission.admin;
                            System.out.print(ConsoleColor.setColor("robot# Congrats, you promoted to '", ConsoleColor.Color.ANSI_YELLOW));
                            System.out.print(ConsoleColor.setColor( "admin", ConsoleColor.Color.ANSI_RED));
                            System.out.println(ConsoleColor.setColor("'.", ConsoleColor.Color.ANSI_YELLOW));
                        } else { System.out.println(ConsoleColor.setColor("robot# Sorry, i can't promote you higher.", ConsoleColor.Color.ANSI_YELLOW)); }
                    } catch (IOException e) {
                        System.out.println("Ошибка?");
                        System.out.println("ввод:" + in);
                        e.printStackTrace();
                    } break;
                }
                case set: {
                    System.out.println(ConsoleColor.setColor("robot# Please choose the table:", ConsoleColor.Color.ANSI_YELLOW));
                    for (Tables table: Tables.values()){
                        System.out.println(ConsoleColor.setColor("\t" + table.toString(), ConsoleColor.Color.ANSI_BLUE));
                    }
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    Tables table;
                    try{
                        table = Tables.valueOf(in = scanner.nextLine());
                    } catch(Exception e){
                        System.out.println(ConsoleColor.setColor("robot# That table does not exist.", ConsoleColor.Color.ANSI_YELLOW));
                        //System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        break;
                    }
                    // таблицу успешно выбрали, теперь нужно заполнить значениями.
                    try {
                        /*Class<?> clazz = Class.forName(table.getClassName());
                        Beans bean = (Beans) clazz.newInstance();*/

                        /*Class<?> clazz = Class.forName(table.getClassName());
                        Constructor<?> constructor = clazz.getConstructor(String.class, Integer.class);
                        Object instance = constructor.newInstance("stringparam", 42);*/

                        /*Class<?> clazz = Class.forName("java.util.Date");
                        Object date = clazz.newInstance();*/

                        Beans bean = (Beans) Class.forName(table.getClassName()).newInstance();
                        Map<String, Object> parameters = new HashMap<>();
                        for (String param : bean.getParameters()){
                            System.out.println(ConsoleColor.setColor("robot# Write field '" + param + "'", ConsoleColor.Color.ANSI_YELLOW));
                            if (bean.getParamClasstype(param) == Integer.class ){
                                while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                                    Object obj = scanner.nextInt();
                                    parameters.put(param, obj);
                                    break;
                                }
                            } else if (bean.getParamClasstype(param) == String.class){
                                Object obj = scanner.nextLine();
                                parameters.put(param, obj);
                            } else if (bean.getParamClasstype(param) == Date.class){
                                while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                                    //2018-12-10T16:41:22
                                    if(!Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}+$").matcher(in).matches())
                                        System.out.println(ConsoleColor.setColor("robot# Wrong date format '2018-12-25T17:55:22'", ConsoleColor.Color.ANSI_YELLOW));
                                else { parameters.put(param, in); }
                                }
                            } else if (bean.getParamClasstype(param).getSuperclass() == Beans.class){
                                // нужно обработать ввод таких сложных.. посути рекурсивный ввод
                                Beans bean_ =  (Beans) Class.forName(bean.getParamClasstype(param).toString()).newInstance();
                                parameters.put(param, consoleCreate(bean_));
                            } else throw new UnsupportedCharsetException("не обработанный класс!" + bean.getParamClasstype(param));

                            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        }
                        bean = BeansFactory.getObject(bean.getClass(), parameters);
                        setRow(bean);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case update: {
                    System.out.println(ConsoleColor.setColor("robot# That command not realized yet.", ConsoleColor.Color.ANSI_YELLOW));
                }
                case delete: {
                    System.out.println(ConsoleColor.setColor("robot# Please choose the table:", ConsoleColor.Color.ANSI_YELLOW));
                    for (Tables table: Tables.values()){
                        System.out.println(ConsoleColor.setColor("\t" + table.toString(), ConsoleColor.Color.ANSI_BLUE));
                    }
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    Tables table;
                    try{
                        table = Tables.valueOf(in = scanner.nextLine());
                    } catch(Exception e){
                        System.out.println(ConsoleColor.setColor("robot# That table does not exist.", ConsoleColor.Color.ANSI_YELLOW));
                        //System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        break;
                    }
                    // таблицу успешно выбрали, теперь нужно запросить Id.
                    System.out.println(ConsoleColor.setColor("robot# Please set row Id to delete.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    int id = scanner.nextInt();
                    Beans bean = deleteRow(table, id);
                    System.out.print(ConsoleColor.setColor("robot# Row with Id:", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor( "\t" + bean.toString(), ConsoleColor.Color.ANSI_RED));
                    System.out.println(ConsoleColor.setColor("robot# was deleted.", ConsoleColor.Color.ANSI_YELLOW));

                    break;
                }
                case help: {
                    for (Command command_: Command.values()){
                        System.out.println(ConsoleColor.setColor("\t" + command_.toString() + "\t-\t" + command_.getDescription(), ConsoleColor.Color.ANSI_WHITE));
                    }
                    break;
                }
                case exit: break;
                default: {
                    System.out.println(ConsoleColor.setColor("robot# Try to write new command. (help)", ConsoleColor.Color.ANSI_YELLOW));
                    //System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    break;
                }
            }
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        System.out.println(ConsoleColor.setColor("robot# See you later!", ConsoleColor.ANSI_BLUE));
    }

    private static void setRow(Class<?> cls, Map<String, Object> parameters){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(BeansFactory.getObject(cls, parameters));

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    private static void setRow(Beans bean){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(bean);

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    private static List<Beans> selectRow(Tables table){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //session.save(BeansFactory.getObject(cls, parameters));
        List<Beans> beans = (List<Beans>) session.createQuery("FROM " + table.getClassName()).list();

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
        return beans;
    }

    private static Beans deleteRow(Tables table, int id){
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //session.save(BeansFactory.getObject(cls, parameters));
        //List<Beans> beans = (List<Beans>) session.createQuery("FROM " + table.getClassName()).list();
        Beans bean = (Beans) session.createQuery("FROM " + table.getClassName() + " WHERE id=:id")
            .setParameter("id", id).list().get(0);
        session.delete(bean);

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
        return bean;
    }

    private Beans consoleCreate(Beans bean) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, Object> parameters = new HashMap<>();
        String in;
        for (String param : bean.getParameters()){
            System.out.println(ConsoleColor.setColor("robot# Write field '" + param + "'", ConsoleColor.Color.ANSI_YELLOW));
            if (bean.getParamClasstype(param) == Integer.class ){
                while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                    Object obj = scanner.nextInt();
                    parameters.put(param, obj);
                    break;
                }
            } else if (bean.getParamClasstype(param) == String.class){
                Object obj = scanner.nextLine();
                parameters.put(param, obj);
            } else if (bean.getParamClasstype(param) == Date.class){
                while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                    //2018-12-10T16:41:22
                    if(!Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}+$").matcher(in).matches())
                        System.out.println(ConsoleColor.setColor("robot# Wrong date format '2018-12-25T17:55:22'", ConsoleColor.Color.ANSI_YELLOW));
                    else { parameters.put(param, in); }
                }
            } else if (bean.getParamClasstype(param).getSuperclass() == Beans.class){
                // нужно обработать ввод таких сложных.. посути рекурсивный ввод
                Beans bean_ =  (Beans) Class.forName(bean.getParamClasstype(param).toString()).newInstance();
                parameters.put(param, consoleCreate(bean_));
            }

            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        return BeansFactory.getObject(bean.getClass(), parameters);
    }




    //=================================================
    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("module_11/res/Hibernate/src/config/hibernate.cfg.xml")) // configures settings from hibernate.config.xml
                .build();
        // "SkillBox_Junior/src/HibernateRes/config/hibernate.cfg.xml"
        // "SkillBox_Junior/module_11/res/Hibernate/src/config/hibernate.cfg.xml"
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
