package module_2;

public class Main {
    private int age_1 = 18;
    private int age_2 = 18;
    private int age_3 = 80;
    private int minAge;
    private int maxAge;
    private double averageAge ;

    public static void main(String[] args) {
        Main main = new Main();
        main.compare();
    }

    public void compare(){

        if (age_1 == age_2) {
            maxAge = age_1 > age_3 ? age_1 : age_3;
            minAge = age_1 < age_3 ? age_1 : age_3;
            averageAge = -1;
        }
        else if (age_1 > age_2 && age_1 > age_3) {
            maxAge = age_1;
            minAge = age_2 < age_3 ? age_2 : age_3;
            averageAge = age_2 < age_3 ? age_3 : age_2;
        }
        else if (age_2 > age_1 && age_2 > age_3){
            maxAge = age_2;
            minAge = age_1 < age_3 ? age_1 : age_3;
            averageAge = age_1 > age_3 ? age_1 : age_3;
        }
        else if (age_3 > age_1 && age_3 > age_2){
            maxAge = age_3;
            minAge = age_1 < age_2 ? age_1 : age_2;
            averageAge = age_1 > age_2 ? age_1 : age_2;
        }

        System.out.println("самый старый : " + maxAge);
        System.out.println("самый молодой : " + minAge);
        System.out.println(averageAge == -1 ? "среднего нет." : "средний возраст: " + averageAge);





    }

}
