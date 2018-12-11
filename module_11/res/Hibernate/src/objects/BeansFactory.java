package module_11.res.Hibernate.src.objects;

import java.util.Map;

public class BeansFactory {

    public static Beans getObject(Class<?> cls, Map<String, Object> parameters){
        if (cls == Vacation.class){
            return new Vacation().createInstance(parameters);
        } else if (cls == Friendship.class) {
            return new Friendship().createInstance(parameters);
        } else if (cls == Employee.class) {
            return new Employee().createInstance(parameters);
        } else if (cls == Department.class) {
            return new Department().createInstance(parameters);
        } else {
            throw new NullPointerException("Не привязана вызываемая Таблица");
        }
    }
}
