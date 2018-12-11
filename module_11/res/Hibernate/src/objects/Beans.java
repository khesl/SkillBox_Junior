package module_11.res.Hibernate.src.objects;

import java.util.Map;

public abstract class Beans {

    public enum Parameters{
        ;

    private Class<?> classType;
    Parameters(Class<?> classType){
        this.classType = classType;
    }
    public Class<?> getClassType() {
        return classType;
    }

    }

    public Beans(){super();}

    public Beans createInstance(Map<String, Object> params) {
        for (Map.Entry entry : params.entrySet())
            setParam(entry.getKey().toString(), entry.getValue());
        return this;
    }
    public abstract void setParam(String param, Object value);
    public abstract Object getParam(String param);

    public String toString(){
        String str = "[";
        for (Parameters param : Parameters.values())
            str += "{\"" + param.toString() + "\":\"" + getParam(param.toString()).toString() + "\"}, ";
        str = str.substring(0, str.length()-1) + "]";
        return str;
    }

    public String[] getParameters(){
        String[] strs = new String[Parameters.values().length];
        int count = 0;
        for (Parameters param: Parameters.values())
            strs[count++] = param.toString();
        return strs;
    }

    public Class<?> getParamClasstype(String param) {
        return Parameters.valueOf(param).getClassType();
    }

}
