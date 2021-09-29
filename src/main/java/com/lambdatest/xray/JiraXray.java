package com.lambdatest.xray;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.net.URL;

public class JiraXray {

    public void APICall() {
        String JiraUserName = "prateeks@lambdatest.com";
        String JiraApiToken = "1yHOI8ELiJ6T7hryDjAQ8A19";
        try {
            String url = "https://" + JiraUserName + ":" + JiraApiToken + "lambdatest.atlassian.net/browse/DEMO-1602";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "multipart/form-data");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());


            wr.flush();
            wr.close();


        } catch (Exception t) {
            System.out.println(t);
        }
    }
}
