package module_7;

import org.junit.Assert;
import org.junit.Test;

public class Lessons {

    private class Calculator{
        public double getCircleSquare(double rad){
            return Math.PI*rad*rad;
        }

        public int getRectangleSquare(int a, int b){
            return a*b;
        }
    }

    @Test
    public void getCircleSquareTest(){
        Calculator calc = new Calculator();
        int actualSqueare = (int) calc.getCircleSquare(17.5d);
        int expectedSquare = 962;
        Assert.assertEquals(expectedSquare, actualSqueare);
    }

    @Test
    public void getRectangleSquareTest(){
        Calculator calc = new Calculator();
        int actualSqueare = calc.getRectangleSquare(6, 5);
        int expectedSquare = 30;
        Assert.assertEquals(expectedSquare, actualSqueare);
    }
}
