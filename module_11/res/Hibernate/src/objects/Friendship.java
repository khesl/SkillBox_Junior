package module_11.res.Hibernate.src.objects;

public class Friendship extends Beans{
    private int id;
    private Employee friendingId;
    private Employee friendedId;

    public Friendship(){
        // For Hibernate
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Employee getFriendingId() { return friendingId; }
    public void setFriendingId(Employee friendingId) { this.friendingId = friendingId; }

    public Employee getFriendedId() { return friendedId; }
    public void setFriendedId(Employee friendedId) { this.friendedId = friendedId; }

    public enum Parameters{
        friendingId    (Employee.class),
        friendedId    (Employee.class);

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
            case friendingId: setFriendingId((Employee) value); break;
            case friendedId: setFriendedId((Employee) value); break;
        }
    }

    @Override
    public Object getParam(String param){
        switch (Parameters.valueOf(param)) {
            case friendingId: return getFriendingId();
            case friendedId: return getFriendedId();
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
