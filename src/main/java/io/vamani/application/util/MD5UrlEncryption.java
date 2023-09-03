package io.vamani.application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;

public class MD5UrlEncryption {
    public static String fakeUrl(String imagePath) throws ParseException, NoSuchAlgorithmException {
        String profile_pics = imagePath.replace("https://vpulse.vamanioverseas.com/mmc_pics/", "/profiles/");
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Date newDate = calendar.getTime();
        long epoch = date.getTime();
        String input =  epoch +profile_pics+" JaiMataDi";
        // System.out.println(input);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
//        byte[] enc = md.digest();
//        String md5Sum = new sun.misc.BASE64Encoder().encode(enc);
//        String hash = md5Sum.substring( 0,md5Sum.length() -2);
        byte[] enc = md.digest();
        byte[] md5Sum = Base64.decodeBase64(enc);
        String md5SumString=md5Sum.toString();
        String hash = md5SumString.substring( 0,md5SumString.length() -2);
        hash = hash.replace("/", "_");
        hash = hash.replace("+", "-");
        hash = hash.replace("=", "");
        return "https://vpulse.vamanioverseas.com"+profile_pics+"?md5="+hash+"&expires="+epoch;
    }

    public static String fakeUploadUrl(String imagePath) throws ParseException, NoSuchAlgorithmException {
        String profile_pics = "/file_upload/"+imagePath;
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        Date newDate = calendar.getTime();
        long epoch = date.getTime();
        String input =  epoch +profile_pics+" JaiMataDi";
        // System.out.println(input);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
//        byte[] enc = md.digest();
//        String md5Sum = new sun.misc.BASE64Encoder().encode(enc);
//        String hash = md5Sum.substring( 0,md5Sum.length() -2);
        byte[] enc = md.digest();
        byte[] md5Sum = Base64.decodeBase64(enc);
        String md5SumString=md5Sum.toString();
        String hash = md5SumString.substring( 0,md5SumString.length() -2);
        hash = hash.replace("/", "_");
        hash = hash.replace("+", "-");
        hash = hash.replace("=", "");
        return "https://vpulse.vamanioverseas.com"+profile_pics+"?md5="+hash+"&expires="+epoch;
        // return "https://devapp.vamanioverseas.com"+profile_pics+"?md5="+hash+"&expires="+epoch;
    }
}
