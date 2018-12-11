package module_11.res.Hibernate.src.objects;

import java.util.Collection;
import java.util.HashSet;

public class Department extends Beans{
    private Integer id;
    private Integer headId;
    private String name;
    private String description;
    private HashSet<Employee> employees = new HashSet<>(0);
    private Employee headEmployees;

    public Department() {
        //Used by Hibernate
    }

    public Department(String name)
    {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public  Employee getHeadEmployees() {
        return headEmployees;
    }

    public void setHeadEmployees(Employee headEmployees) { this.headEmployees = headEmployees; }

    public enum Parameters{
        headId          (Integer.class),
        name            (String.class),
        description     (String.class),
        employees       (HashSet.class),
        headEmployees   (Employee.class);

        private Class<?> classType;

        Parameters(Class<?> classType){
            this.classType = classType;
        }

        public Class<?> getClassType() {
            return classType;
        }
    }

    @Override
    public void setParam(String param, Object value) {
        switch (Parameters.valueOf(param)){
            case headId: setHeadId((int) value); break;
            case name: setName((String) value); break;
            case description: setDescription((String) value); break;
            case employees: setEmployees((HashSet) value); break;
            case headEmployees: setHeadEmployees((Employee) value); break;
        }
    }

    @Override
    public Object getParam(String param){
        switch (Parameters.valueOf(param)) {
            case headId: return getHeadId();
            case name: return getName();
            case description: return getDescription();
            case employees: return getEmployees();
            case headEmployees: return getHeadEmployees();
            default: return null;
        }
    }

    @Override
    public String[] getParameters(){
        String[] strs = new String[Parameters.values().length];
        int count = 0;
        for (Parameters param: Parameters.values())
            strs[count++] = param.toString();
        return strs;
    }
    @Override
    public Class<?> getParamClasstype(String param) {
        return Parameters.valueOf(param).getClassType();
    }
}
