package module_11.res.Hibernate.src.objects;

import java.util.Date;

public class Vacation extends Beans{
    private int id;
    private Employee employee;
    private Date startDate;
    private Date endDate;

    public Vacation(){
        // for Hibernate
    }
    public Vacation(Employee employee, Date startDate, Date endDate){
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public enum Parameters{
        employee    (Employee.class),
        startDate   (Date.class),
        endDate     (Date.class);

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
            case employee: setEmployee((Employee) value); break;
            case startDate: setStartDate((Date) value); break;
            case endDate: setStartDate((Date) value); break;
        }
    }

    @Override
    public Object getParam(String param){
        switch (Parameters.valueOf(param)) {
            case employee: return getEmployee();
            case startDate: return getStartDate();
            case endDate: return getStartDate();
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
            str += "{\"" + param.toString() + "\":\"" + getParam(param.toString()).toString() + "\"}, ";
        str = str.substring(0, str.length()-1) + "]";
        return str;
    }
}
