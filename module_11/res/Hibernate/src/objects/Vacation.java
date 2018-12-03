package module_11.res.Hibernate.src.objects;

import java.util.Date;

public class Vacation {
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
}
