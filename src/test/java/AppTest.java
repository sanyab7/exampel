import model.Register;
import model.SuccsesReg;
import model.UsersGoRest;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import restApi.GorestCoInApi;
import restApi.RestClient;

import java.util.List;
import java.util.Random;

public class AppTest {
    static Logger logger = Logger.getLogger(AppTest.class);

    @Test
    public void test1GetMethod() {
        GorestCoInApi.getMethodGorest();
    }

    @Test
    public void test2GetMethodBasepath() {
        GorestCoInApi myObektid = new GorestCoInApi();
        myObektid.getMethodbasepath("/2406");
    }

    @Test
    public void test3GetMethodPrintNameForId() {
        List<UsersGoRest> usergo = GorestCoInApi.getMethodGorest();
        Assert.assertTrue(usergo.size() > 0);
        int id1obektid = usergo.get((0)).getId();
        logger.info("user id =" + id1obektid);
        GorestCoInApi printName = new GorestCoInApi();
        printName.getMethodPrintNameId(id1obektid);
    }

    @Test
    public void test4NewUserPostCheckDelite() {
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

        GorestCoInApi user1 = new GorestCoInApi();
        Register user = user1.userNew(name, email, gender, status);

        SuccsesReg succsesReg = RestClient.postMethod(user); // ????

        Assert.assertNotNull(succsesReg.getId());

        Assert.assertEquals(name, succsesReg.getName());
        Assert.assertEquals(email, succsesReg.getEmail());
        Assert.assertEquals(gender, succsesReg.getGender());
        Assert.assertEquals(status, succsesReg.getStatus());
        int idObect = succsesReg.getId();
        logger.info(idObect);

        GorestCoInApi deleteObekt = new GorestCoInApi();
        deleteObekt.deleteObektId(idObect);
    }

    @Test
    public void test5() {
        GorestCoInApi mytestgorest = new GorestCoInApi();
        mytestgorest.getGorest();

    }
}
