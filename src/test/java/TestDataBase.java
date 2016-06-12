/*
import com.synycs.truckbay.common.BCrypt;
import com.synycs.truckbay.common.DateRound;
import com.synycs.truckbay.server.UserDetails;
import com.synycs.truckbay.server.dao.UserDetailsDAO;
import com.synycs.truckbay.server.latepayment.state.Status;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

*/
/**
 * Created by hadoop on 18/05/15.
 *//*

public class TestDataBase {

    public static void main(String []args) throws SQLException {
        //ServerContext sc=ServerContext.getInstance();


    try {

*/
/*

        System.out.println(Status.PAYMENT_PENDING.toString());

        for(int i=0;i<10;i++) {
            String gensalt = BCrypt.gensalt(12);
            String out=BCrypt.hashpw("123456", gensalt);
            System.out.println(out);
        }

        String sr="$2a$12$T5/KAyIWjtO2/hM2IjV/ueOrMtWOIw61ZpWbOvfxRhU2DWL68AqZa";
        System.out.println(BCrypt.checkpw("123456", new String(sr.toCharArray())));
*//*




        */
/*UserDetails userDetails = new UserDetailsDAO("user1").validateUserAndGetDetails();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Calcutta"));
        DateTime dt = new DateTime(cal.getTime());
        DateTimeZone dtZone = DateTimeZone.forID("Asia/Calcutta");
        DateTime dtus = dt.withZone(dtZone);
        Date dateInIndia = dtus.toLocalDateTime().toDate();
        userDetails.setFormId(userDetails.getLoginName());
        userDetails.setCustomerLoginName("admin");
        userDetails.setCustomerPassword("admin");
        userDetails.setLastModifiedBy("self");
        userDetails.setMobileNumber("9611289882");
        userDetails.setEmail("naga1@gmail.com");
        userDetails.setFormId(userDetails.getLoginName());

        userDetails.setLastModifiedDate(dateInIndia);
        userDetails.setCreationDate(dateInIndia.getTime());
*//*


    }
    catch (Exception e){
      e.printStackTrace();
    }





    }
}
*/
