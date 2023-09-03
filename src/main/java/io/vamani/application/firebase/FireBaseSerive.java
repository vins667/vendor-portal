package io.vamani.application.firebase;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

import java.util.Date;

public class FireBaseSerive {
    public static void main(String args[]) throws  Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://fcm.googleapis.com/fcm/send");
        post.setHeader("Content-type", "application/json");
        post.setHeader("Authorization", "key=AIzaSyC8nVGTtZxl9vY2wVAlvXm-2Hjx5qyS3SM");

        JSONObject message = new JSONObject();
        message.put("to", "egAUD7TrSKg:APA91bGFNOGHsdib85fLFhcYWDnBV10ex9J8LT7hGyhJv77Sbr11b_6AankL75J0On_pWuN7QIrwPZfn2A5ogm06uPTzUtPYhw_6uewCKPCXH2txjgPk2euW3DAnHQisSd2CSscoBUAk");
        message.put("priority", "high");

        JSONObject notification = new JSONObject();


        JSONObject notificationBody = new JSONObject();
        notificationBody.put("body", "Leave sanction request for Casual Leave by Vivek Kumar Jaiswal.");
        notificationBody.put("type", "Leave Approval");
        notificationBody.put("status", "Pending");
        notification.put("body", notificationBody);

        message.put("notification", notification);

        post.setEntity(new StringEntity(message.toString(), "UTF-8"));
        HttpResponse response = client.execute(post);
        System.out.println(response);
        System.out.println(message);
    }

}
