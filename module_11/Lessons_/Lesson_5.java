package module_11.Lessons_;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.List;

public class Lesson_5 {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        /** подзадачи разбиты внутри методов. */
        Lesson_5();
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
