package com.assignment.demo.util;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestUtil {

    private String apiUrl;

    public RestUtil(String url) {
        this.apiUrl = url;
    }

    private URL restUrl() throws MalformedURLException {
        return new URL(apiUrl);
    }

    private HttpURLConnection getConnection(URL url) throws HttpClientErrorException, IOException {
        return (HttpURLConnection) url.openConnection();
    }

    public Object callGetApi() throws Exception {
        URL url = restUrl();
        HttpURLConnection connection = getConnection(url);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode :: " + responseCode);
        }
        JSONParser parser = new JSONParser(url.openStream());
        return parser.parse();
    }
}
