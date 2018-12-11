package module_11;

import module_11.res.Hibernate.src.objects.Department;
import module_11.res.Hibernate.src.objects.Employee;
import module_11.res.Hibernate.src.objects.Friendship;
import module_11.res.Hibernate.src.objects.Vacation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Lessons {

    public static void main(String[] args) {

        /**
         *  подзадачи разбиты внутри методов. */
        //Lesson_3_video();
        //Lesson_3();
        //Lesson_4();
        Lesson_5();
    }


    private static SessionFactory sessionFactory;
    private static void Lesson_3_video(){
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
        for(Department department : departments) {
            System.out.println(department.getName() + " : " + department.getHeadEmployees().getName());
        }

        // обращение через hibernate к базе
        /*List<Object> objects = (List<Object>)session.createSQLQuery("select * from department").list();
        for (Object object : objects){ Object values[] = (Object[]) object; System.out.println(values[2]); }*/
        /*List<Object> objects = (List<Object>)session.createSQLQuery("select name from department").list();
        for (Object object : objects){ System.out.println(object); }*/


        // создание запись в таблицу
//        Department dept = new Department("Отдел производства");
//        session.save(dept);
//        System.out.println(dept.getId());

        // удаление записи по полю
//        Department dept = (Department) session.createQuery("FROM Department WHERE name=:name")
//            .setParameter("name", "Отдел производства").list().get(0);
//        session.delete(dept);

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    private static void Lesson_3(){
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // - Прописать связь one­to­one для поля headId.
        {
            List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
            for(Department department : departments) {
                System.out.println(department.getName() + " " + department.getHeadId() + " : "
                    + department.getHeadEmployees().getName() + " " + department.getHeadEmployees().getId());
            }
        }


        //-- 1. вывести ошибочно привязанных сотрудников, которые работают в одних отделах, а руководят другими;
        //select * from learn.employee e, learn.department d where e.department_id != d.id and d.head_id = e.id;
        {
            List<Department> departments = (List<Department>) session.createQuery(
                "FROM Department d where d.id != d.headEmployees.department.id").list();
            for(Department department : departments) {
                System.out.println(department.getName() + " " + department.getHeadId() + " : "
                        + department.getHeadEmployees().getName() + " " + department.getHeadEmployees().getId()
                        + " from (" + department.getHeadEmployees().getDepartment().getName() + ")");
            }
        }

        //-- 2. вывести руководителей отделов, зарплата которых составляет менее 115 000 рублей в месяц;
        //select * from learn.employee e, learn.department d where d.head_id = e.id and e.salary <= 115000;
        {
            List<Department> departments = (List<Department>) session.createQuery("FROM Department d where d.headEmployees.salary <= 115000").list();
            for(Department department : departments) {
                Employee employee = department.getHeadEmployees();
                System.out.println(employee.getName() + " " + employee.getId()
                        + " from (" + employee.getDepartment().getName() + ") salary = " + employee.getSalary());
            }
        }

        //-- 3. вывести руководителей отделов, которые вышли на работу до марта
        //select * from learn.employee e, learn.department d where d.head_id = e.id and extract(MONTH FROM e.hire_date) < 4;
        {
            List<Department> departments = (List<Department>) session.createQuery("FROM Department d where extract(MONTH FROM d.headEmployees.hireDate) < 4").list();
            for(Department department : departments) {
                Employee employee = department.getHeadEmployees();
                System.out.println(employee.getName() + " " + employee.getId()
                        + " from (" + employee.getDepartment().getName() + ") hire_date = " + employee.getHireDate());
            }
        }

        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
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
    private static boolean dateOverlap(Date firstStart,Date firstEnd, Date secondStart,Date secondEnd){
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

    private static void Lesson_5(){
        // - Написать SQL­запросы, которые будут выводить:
        //○ Список отделов с количеством сотрудников в каждом из них
        //○ Список отделов, в которых работает меньше трёх сотрудников
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        /*List<Employee> employees = (List<Employee>) session.createQuery("from Employee e order by e.salary");
        for (Employee employee : employees) {
            System.out.println(employee.getSalary() + "\t"  + employee.getName());
        }*/

        /*List<Object[]> objects = (List<Object[]>) session.createQuery(
                "select d.name, sum(e.salary) from Employee e " +
                   "join e.department d group by d.id").list();
        for (Object[] data : objects) {
            System.out.println(data[1] + "\t"  + data[0]);
        }*/

        //○ Список отделов с количеством сотрудников в каждом из них
        /**select d.name, count(e.name) from learn.department d
            left join learn.employee e ON d.id = e.department_id
            group by e.department_id;*/
        /**select d.name, count(e.name) 'количество сотрудников' from learn.employee e
            inner join learn.department d ON e.department_id = d.id
            group by d.id;*/
        List<Object[]> objects = (List<Object[]>) session.createQuery(
                "select d.name, count(e.name) as count_ from Employee e " +
                        "join e.department d group by d.id").list();
        for (Object[] data : objects) {
            System.out.println(data[1] + "\t"  + data[0]);
        }

        //○ Список отделов, в которых работает меньше трёх сотрудников
        /**select d.name, ee.count_ 'количество сотрудников' from learn.department d
            left join (
	            select e.department_id, d.name, count(e.name) count_ from learn.employee e
		            inner join learn.department d ON e.department_id = d.id
		            group by d.id) ee
            ON d.id = ee.department_id where ee.count_ < 3
            group by ee.department_id*/


        session.getTransaction().commit();
        session.close();

        //==================================================================
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    //=====================================================================

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
