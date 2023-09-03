package io.vamani.application.config;

import io.github.jhipster.config.JHipsterConstants;
import io.vamani.application.firebase.FirebaseSystem;
import io.vamani.application.model.FirebaseConfig;
import io.vamani.application.model.SmsConfig;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.net.MalformedURLException;

@Configuration
public class FirebaseConfigration {
    private final Logger log = LoggerFactory.getLogger(FirebaseConfigration.class);
    @Autowired
    private Environment env;

    private final String USER_AGENT = "Mozilla/5.0";

    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public FirebaseConfig getSmsConfig() {
        return new FirebaseConfig(env.getProperty("spring.firebase.url"), env.getProperty("spring.firebase.key"));
    }

    public int postFirebase(FirebaseSystem firebaseSystem) throws MalformedURLException, IOException {
        FirebaseConfig firebaseConfig = getSmsConfig();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(firebaseConfig.getUrl());
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", firebaseConfig.getKey());

        JSONObject message = new JSONObject();
        message.put("to", firebaseSystem.getDeviceId());
        message.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "vPulse");
        notification.put("type", firebaseSystem.getType());
        notification.put("header", firebaseSystem.getType());
        notification.put("status", firebaseSystem.getStatus());


        message.put("data", notification);

        post.setEntity(new StringEntity(message.toString(), "UTF-8"));
        HttpResponse response = client.execute(post);
        log.info("Firebase Configration " + response);
        return response.getStatusLine().getStatusCode();
    }
}
