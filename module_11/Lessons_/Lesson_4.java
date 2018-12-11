package module_11.Lessons_;

import module_11.res.Hibernate.src.objects.Employee;
import module_11.res.Hibernate.src.objects.Vacation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Lesson_4 {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        /** подзадачи разбиты внутри методов. */
        Lesson_4();
    }

    /**
     * - Создать код, который будет порождать таблицу с отпусками сотрудников и заполнять
     *      её тестовыми данными следующего виде: по 4 3 ­недели отпуска ежегодно в
     *      случайное время со дня найма до конца следующего года.
     *
     * - Написать код, который выводит пересечения отпусков сотрудников в текущем и
     * */
    private static void Lesson_4(){
        setUp();

        // create a couple of events...
        Session session;
        try {
            session = sessionFactory.openSession();
        } catch (Exception e) {
            System.out.println("Any Table does not exists before.");
            setUp();
            session = sessionFactory.openSession();
        }

        session.beginTransaction();

        /*List<Friendship> friendships = (List<Friendship>) session.createQuery("FROM Friendship").list();
        for(Friendship friendship : friendships) {
            System.out.println(friendship.getName() + " : " + friendship.getHeadEmployees().getName());
        }*/

        // - Создать код, который будет порождать таблицу с отпусками сотрудников и заполнять её тестовыми данными
        // следующего виде: по 4 3 ­недели отпуска ежегодно в случайное время со дня найма до конца следующего года.

        // создаём таблицу
        boolean isTableEmpty = false;
        {
            int count = session.createQuery("FROM Vacation").list().size();
            System.out.println("number   : " + count);
            if (count == 0) isTableEmpty = true;
        }

        // заполняем таблицу, если она пуста
        if (isTableEmpty) {
            List<Employee> employees = (List<Employee>) session.createQuery("FROM Employee").list();
            for (Employee employee : employees) {
                //System.out.println(employee.getName() + " " + employee.getId()
                //      + " from (" + employee.getDepartment().getName() + ") salary = " + employee.getSalary());
                for (Vacation vacation : createVacations(employee)) session.save(vacation);
            }
        }

        // - Написать код, который выводит пересечения отпусков сотрудников в текущем и след году
        List<Vacation> vacations = (List<Vacation>) session.createQuery("FROM Vacation").list();
        for (int i = 0; i < vacations.size(); i++) {
            Vacation vacation = vacations.get(i);
            for (int j = i; j < vacations.size(); j++){
                Vacation vacation_  = vacations.get(j);
                if (vacation.getId() != vacation_.getId()){
                    if (dateOverlap(vacation.getStartDate(), vacation.getEndDate(), vacation_.getStartDate(), vacation_.getEndDate()))
                        System.out.println("Vacation of '" + vacation.getEmployee().getName() + "' overlaps with '" + vacation_.getEmployee().getName() + "', "
                                + vacation.getStartDate() + "-" + vacation.getEndDate() + " :: " + vacation_.getStartDate() + "-" + vacation_.getEndDate());
                }
            }
        }


        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }
    private static List<Vacation> createVacations(Employee employee){
        List<Vacation> vacations = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(employee.getHireDate());
        for (int i = cal.get(Calendar.YEAR); i <= Calendar.getInstance().get(Calendar.YEAR) + 1; i++){
            int dayOfVacation = (int)(21 + Math.random() * 7);
            int startDay = (int)(Math.random()*365);
            if (i == cal.get(Calendar.YEAR))
                if (startDay + cal.get(Calendar.DAY_OF_YEAR) > 365)
                    startDay = (int)((365 - cal.get(Calendar.DAY_OF_YEAR)) * Math.random());

            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.YEAR, i);
            startDate.set(Calendar.DAY_OF_YEAR, startDay);
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(startDate.getTime());
            endDate.add(Calendar.DAY_OF_YEAR, dayOfVacation);
            System.out.println(startDate.getTime() + " : " + endDate.getTime());
            vacations.add(new Vacation(employee, startDate.getTime(), endDate.getTime()));
        }
        return vacations;
    }
    private static boolean dateOverlap(Date firstStart, Date firstEnd, Date secondStart, Date secondEnd){
        Calendar calFirstStart = Calendar.getInstance(), calFirstEnd= Calendar.getInstance(),
                calSecondStart = Calendar.getInstance(), calSecondEnd = Calendar.getInstance();
        calFirstStart.setTime(firstStart);
        calFirstEnd.setTime(firstEnd);
        calSecondStart.setTime(secondStart);
        calSecondEnd.setTime(secondEnd);

        return (calFirstStart.before(calSecondStart)) ?
                (calFirstEnd.after(calSecondStart)) ? true : false
                :
                (calSecondEnd.after(calFirstStart)) ? true : false;
        // [  >  ]
        //    [  <  ]
    }

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("SkillBox_Junior/module_11/res/Hibernate/src/config/hibernate.cfg.xml")) // configures settings from hibernate.config.xml
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
