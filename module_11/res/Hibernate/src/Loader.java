package module_11.res.Hibernate.src;

import module_11.res.Hibernate.src.objects.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.List;

/**
 * Created by Danya on 26.10.2015.
 *
 * SELECT version();
 *
 * SET GLOBAL time_zone = '+6:00';
 */
public class Loader
{
    private static SessionFactory sessionFactory;

    public static void main(String[] args)
    {
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
        for(Department department : departments) {
            System.out.println(department.getName());
        }

//        Department dept = new Department("Отдел производства");
//        session.save(dept);
//        System.out.println(dept.getId());

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

    //=====================================================================

    private static void setUp()
    {
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
