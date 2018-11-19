package JUnitTests;

import Utils.MyUtils;
import org.junit.Assert;
import org.junit.Test;

public class MyUtilsTester {

    @Test
    public void getPhoneNumberTest(){
        String actualValue = MyUtils.getPhoneNumber("+7(777)0000000");
        String expectedValue = "+77770000000";
        Assert.assertEquals(actualValue,expectedValue);
    }
    @Test
    public void getPhoneNumberTest1(){
        String actualValue = MyUtils.getPhoneNumber("+7(777)000-00-00");
        String expectedValue = "+77770000000";
        Assert.assertEquals(actualValue,expectedValue);
    }
    @Test
    public void getPhoneNumberTest2(){
        String actualValue = MyUtils.getPhoneNumber("+7(777)000 00 00");
        String expectedValue = "+77770000000";
        Assert.assertEquals(actualValue,expectedValue);
    }

    @Test
    public void checkPhoneNumberTest(){
        Assert.assertTrue(MyUtils.checkPhoneNumber("+7 777 0000000"));
        Assert.assertTrue(MyUtils.checkPhoneNumber("+77771234567"));
        Assert.assertTrue(MyUtils.checkPhoneNumber("87771234567"));
        Assert.assertTrue(MyUtils.checkPhoneNumber("+7(777)1234567"));
        Assert.assertTrue(MyUtils.checkPhoneNumber("+7-777-123-45-67"));
    }
}
