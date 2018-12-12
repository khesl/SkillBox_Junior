package module_11.res.Hibernate.src.objects;

import java.util.Date;

public class Employee extends Beans {
    private Integer id;
    private Date hireDate;
    private Integer salary;
    private String name;
    private Department department;

    public Employee(){
        //Used by Hibernate
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public enum Parameters{
        hireDate        (Date.class),
        salary          (Integer.class),
        name            (String.class),
        department      (Department.class);

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
            case hireDate: setHireDate((Date) value); break;
            case salary: setSalary((Integer) value); break;
            case name: setName((String) value); break;
            case department: setDepartment((Department) value); break;
        }
    }

    @Override
    public Object getParam(String param){
        switch (Parameters.valueOf(param)) {
            case hireDate: return getHireDate();
            case salary: return getSalary();
            case name: return getName();
            case department: return getDepartment();
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

    @Override
    public String toString(){
        String str = "[";
        for (Parameters param : Parameters.values())
            if (param == Parameters.department)
                str += "{\"" + param.toString() + "\":\"" + getDepartment().getId() + "\"}, ";
            else
            str += "{\"" + param.toString() + "\":\"" + getParam(param.toString()).toString() + "\"}, ";
        str = str.substring(0, str.length()-1) + "]";
        return str;
    }
}
