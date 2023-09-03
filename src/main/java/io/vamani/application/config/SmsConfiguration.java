package io.vamani.application.config;

import io.github.jhipster.config.JHipsterConstants;
import io.vamani.application.model.OtpSystem;
import io.vamani.application.model.SmsConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Configuration
public class SmsConfiguration {
    private final Logger log = LoggerFactory.getLogger(SmsConfiguration.class);
    @Autowired
    private Environment env;

    private final String USER_AGENT = "Mozilla/5.0";

    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public SmsConfig getSmsConfig() {
        return new SmsConfig(env.getProperty("spring.sms.url"), env.getProperty("spring.sms.username"), env.getProperty("spring.sms.password"), env.getProperty("spring.sms.send"), env.getProperty("spring.sms.type"));
    }

    public int postSms(OtpSystem otpSystem) throws MalformedURLException, IOException {
        SmsConfig smsConfig = getSmsConfig();
        if(smsConfig != null && smsConfig.getType() != null && smsConfig.getType().equalsIgnoreCase("bol7")) {
            return bol7Sms(smsConfig, otpSystem);
        } else if(smsConfig != null && smsConfig.getType() != null && smsConfig.getType().equalsIgnoreCase("textlocal")) {
            return textLocalSms(smsConfig, otpSystem);
        } else{
            return 0;//Setting issue
        }
    }

    // Bol 7
    protected int bol7Sms(SmsConfig smsConfig, OtpSystem otpSystem) throws MalformedURLException, IOException {
        String url = smsConfig.getUrl() + "?uname=" + URLEncoder.encode(smsConfig.getUsername()) + "&pass=" + URLEncoder.encode(smsConfig.getPassword()) + "&send=" + URLEncoder.encode(smsConfig.getSend()) + "&dest=" + URLEncoder.encode(otpSystem.getMobileNumber()) + "&msg=" + URLEncoder.encode(otpSystem.getMessage()) + "&priority=1";
        log.debug(url);
        URL obj = new URL(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        log.info("Bol7 "+otpSystem.getMobileNumber()+ " (" +responseString+")");
        return response.getStatusLine().getStatusCode();
    }

    // TextLocal
    protected int textLocalSms(SmsConfig smsConfig, OtpSystem otpSystem) throws MalformedURLException, IOException {
        // Construct data
        String apiKey = "apikey=" + URLEncoder.encode(smsConfig.getUsername());
        String message = "&message=" + URLEncoder.encode(otpSystem.getMessage());
        String sender = "&sender=" + URLEncoder.encode(smsConfig.getSend());
        String numbers = "&numbers=" + URLEncoder.encode(otpSystem.getMobileNumber());
        // Send data
        HttpURLConnection conn = (HttpURLConnection) new URL(smsConfig.getUrl()+"?").openConnection();
        String data = apiKey + numbers + message + sender;
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
        conn.getOutputStream().write(data.getBytes("UTF-8"));
        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        final StringBuffer stringBuffer = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            stringBuffer.append(line);
        }
        rd.close();
        if(stringBuffer != null && stringBuffer.length()>0) {
            log.info("TextLocal "+otpSystem.getMobileNumber()+ " (" +stringBuffer.toString()+")");
            return 200;
        } else {
            return 404;
        }
    }
}
