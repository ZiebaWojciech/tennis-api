package pl.coderslab.tennisApi.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.service.EventService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataPosting {
    private final String TENNIS_BET_URL = "http://localhost:8080/get";
//    @GetMapping("/sendFeed")
//    public void sendAllEvents() throws IOException {
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(TENNIS_BET_URL);
//
//        jsonService.createJsonFromGame(g);
//        String json = jsonService.getJsonFromGame().toString();
//        StringEntity entity = new StringEntity(json);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//        CloseableHttpResponse response = client.execute(httpPost);
//        client.close();
//    }
//}
    @Autowired
    EventService eventService;
    @Autowired
    JsonCreateService jsonCreateService;
    //TODO make automatic send
    @GetMapping("/createJson")
    public List<String> sendJason(){
        List<String> jsons = new ArrayList<>();
        try {
            jsons = jsonCreateService.createJsonEvent(eventService.getAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsons;
    }
}
