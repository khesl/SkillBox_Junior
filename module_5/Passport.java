package module_5;

import Utils.MyUtils;

public class Passport implements Comparable<Passport>{
    private int seria;
    private int number;
    private String name;
    private String surname;
    private String patronimyc;

    public Passport(int seria, int number){
        this.seria = seria;
        this.number = number;
    }

    public Passport(int seria, int number, String name, String surname, String patronimyc){
        setSeria(seria);
        setNumber(number);
        this.name = name;
        this.surname = surname;
        this.patronimyc = patronimyc;
    }

    public static Passport generatePassport(){
        int seriaNum = (int)(10 + 89 * Math.random());
        int seriaYear = (int)(18 * Math.random());
        int passNum = (int)(10000 + 970000 * Math.random());
        return new Passport((seriaNum*100 + seriaYear), passNum, MyUtils.getRamdonName(), MyUtils.getRamdonName(), MyUtils.getRamdonName());
    }


    public void setSeria(int seria){
        if (seria > 10000 || seria < 100) throw new IllegalArgumentException("Wrong Seria value");
        this.seria = seria;
    }
    public int getSeria() { return seria; }
    public void setNumber(int number){
        if (number < 10000 || seria > 999999) throw new IllegalArgumentException("Wrong Number value");
        this.number = number;
    }
    public int getNumber() { return number; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getSurname() { return surname; }
    public  void setPatronimyc(String patronimyc) { this.patronimyc = patronimyc; }
    public String getPatronimyc() { return patronimyc; }

    public String toString(){
        return (seria/100) < 10 ? "0" + (seria/100) : String.valueOf((seria/100)) + " " + ((seria%100) < 10 ? "0" + (seria%100) : String.valueOf((seria%100))) + " " + (number < 100000 ? "0" + number : number);
    }
    public String getFIO(){
        return name.substring(0, 1).toUpperCase() + name.substring(1, name.length()) + " "
                + surname.substring(0, 1).toUpperCase() + surname.substring(1, surname.length()) + " "
                + patronimyc.substring(0, 1).toUpperCase() + patronimyc.substring(1, patronimyc.length());
    }

    @Override
    public boolean equals(Object obj) {
        return ((Passport) obj).getSeria() == seria &&
                ((Passport) obj).getNumber() == number;
    }

    @Override
    public int compareTo(Passport o) {
        if (this.equals(o)) return 0;
        return Integer.valueOf(this.toString().replaceAll(" ", "")) >
                Integer.valueOf(o.toString().replaceAll(" ", "")) ? 1 : -1;
    }
}
