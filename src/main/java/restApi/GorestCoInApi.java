package restApi;

import constants.Сonstant;
//import jdk.vm.ci.meta.Constant;
import model.Register;
import model.SuccsesReg;
import model.UsersGoRest;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class GorestCoInApi {
    static Logger logger = Logger.getLogger(GorestCoInApi.class);

    public void getGorest() {

        String email = "tenali1.ramakri2shna@15ce.com";
        int aRamdomNumber = (int) (Math.random() * 100);
        Random random = new Random();
        String str = "abcdefghijklmnopqrstuvwxyz";
        char chRamdomText = str.charAt(random.nextInt(str.length()));
        email = chRamdomText + "t" + aRamdomNumber + email;
        logger.info(email);

        String name = "Tenali Ramakrishna";
        String gender = "male";
        String status = "active";
        Register user = new Register("Tenali Ramakrishna", email, "male", "active");
        // Register user = RestClient.user("Tenali Ramakrishna", email, "male", "active");
        SuccsesReg succsesReg = RestClient.postMethod(user);
        Assert.assertNotNull(succsesReg.getId());

        Assert.assertEquals(name, succsesReg.getName());
        Assert.assertEquals(email, succsesReg.getEmail());
        Assert.assertEquals(gender, succsesReg.getGender());
        Assert.assertEquals(status, succsesReg.getStatus());


        int idNewObject = succsesReg.getId();
        logger.info(idNewObject);
        List<UsersGoRest> usergo = RestClient.getMethodListObjects(Сonstant.URL);

        restApi.RestClient myDelete = new restApi.RestClient();
        myDelete.deleteMethod(String.valueOf(idNewObject));
    }

    public static Register userNew(String name, String email, String gender, String status) {
        Register user = new Register(name, email, gender, status);
        return user;
    }


    public static List<UsersGoRest> getMethodGorest() {
        List<UsersGoRest> usergo = RestClient.getMethodListObjects(Сonstant.URL);

        return usergo;
    }

    public void getMethodbasepath(String basepath) {
        RestClient mygetbase = new RestClient();
        mygetbase.getMethod(Сonstant.URL, basepath);
    }

    public void getMethodPrintNameId(int id1obektid) {
        RestClient myGet = new RestClient();
        UsersGoRest obektid = myGet.getMethodReturn(String.valueOf(id1obektid));
        logger.info("user name is =" + obektid.getName());
    }

    public void deleteObektId(int idObect) {
        RestClient myDelete = new RestClient();
        myDelete.deleteMethod(String.valueOf(idObect));
    }

}
