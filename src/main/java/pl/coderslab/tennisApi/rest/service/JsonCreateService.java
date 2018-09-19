package pl.coderslab.tennisApi.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.enumUtil.EventStatus;
import pl.coderslab.tennisApi.service.EventService;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;

@Service
public class JsonCreateService {

    public List<String> createJsonEvent(List<Event> events) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        List<String> jsons = new ArrayList<>();
        for (Event event : events) {
            jsons.add(mapper.writeValueAsString(event));
        }
        return jsons;
    }
}