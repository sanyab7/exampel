import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


public class AppTest {
    static Logger logger = Logger.getLogger(AppTest.class);

    @Test
    public void test1() {
      List<UsersGoRest> usergo = RestClientJava.getMethodListObjects();

    }

    @Test
    public void test2() {
        RestClientJava myGet = new RestClientJava();
        myGet.getMethod("/2406");
    }

    @Test
    public void test3() {

        List<UsersGoRest> usergo = RestClientJava.getMethodListObjects();

        Assert.assertTrue(usergo.size() > 0);
        int mm = usergo.get((0)).getId();
        logger.info("user id =" + mm);

        RestClientJava myGet = new RestClientJava();
        UsersGoRest obektid = myGet.getMethodReturn(String.valueOf(mm));
                       logger.info("user name is =" + obektid.getName());
    }

    @Test
    public void test4() {
        String name = "Tenali Ramakrishna";
        String email = "tenali1.ramakri2shna@15ce.com";
        int aRamdomNumber = (int) (Math.random() * 100);
        Random random = new Random();
        String str = "abcdefghijklmnopqrstuvwxyz";
        char chRamdomText = str.charAt(random.nextInt(str.length()));
        email = chRamdomText + "t" + aRamdomNumber + email;
        logger.info(email);
        String gender = "male";
        String status = "active";

        Register user = new Register("Tenali Ramakrishna", email, "male", "active");
        SuccsesReg succsesReg = RestClientJava.postMethod(user);

        Assert.assertNotNull(succsesReg.getId());

        Assert.assertEquals(name, succsesReg.getName());
        Assert.assertEquals(email, succsesReg.getEmail());
        Assert.assertEquals(gender, succsesReg.getGender());
        Assert.assertEquals(status, succsesReg.getStatus());
        int idObect = succsesReg.getId();
        logger.info(idObect);

        RestClientJava myDelete = new RestClientJava();
        myDelete.deleteMethod(String.valueOf(idObect));
    }
}
