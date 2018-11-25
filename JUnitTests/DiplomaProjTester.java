package JUnitTests;

import Diploma.src.DiplomaProj;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DiplomaProjTester {

    @Test(expected = IOException.class)
    public void getSmsAuthorisationTest() throws IOException {
        DiplomaProj diploma = new DiplomaProj();
        diploma.setPhone("+777700000000");
        diploma.getSmsAuthorisation();
    }

    @Test
    public void getSmsAuthorisationTest_2() {
        DiplomaProj diploma = new DiplomaProj();
        diploma.setPhone("+77771652384");
        try {
            diploma.getSmsAuthorisation();
        } catch (IOException e) {
            //e.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}
