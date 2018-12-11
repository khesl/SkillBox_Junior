package module_11.Lessons_;

import module_11.res.Hibernate.src.objects.Department;
import module_11.res.Hibernate.src.objects.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.List;

public class Lesson_3 {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        Lesson_3();
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
