import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

public class Nginx {
    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {

        String fileName = "https://vpulse.vamanioverseas.com/mmc_pics/102000046.jpg";//"/file_upload_s/news/2.jpg";
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
        Date newDate = calendar.getTime();
        long epoch = date.getTime();

        String input =  epoch +fileName+" enigma";
        System.out.println(input);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        /*byte[] enc = md.digest();
        String md5Sum = new sun.misc.BASE64Encoder().encode(enc);
        String hash = md5Sum.substring( 0,md5Sum.length() -2);*/
        byte[] enc = md.digest();
        byte[] md5Sum = Base64.decodeBase64(enc);
        String md5SumString=md5Sum.toString();
        String hash = md5SumString.substring( 0,md5SumString.length() -2);
        hash = hash.replace("/", "_");
        hash = hash.replace("+", "-");
        hash = hash.replace("=", "");

        String   link = "https://devapp.vamanioverseas.com"+fileName+"?md5="+hash+"&expires="+epoch;

        System.out.println(link);
    }
}
