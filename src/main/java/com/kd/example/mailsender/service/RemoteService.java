package com.kd.example.mailsender.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kd.example.mailsender.beans.Employee;
import com.kd.example.mailsender.beans.Filter;
import com.kd.example.mailsender.exception.BWisherException;

@Service
public class RemoteService implements DataService {
    private ObjectMapper mapper = new ObjectMapper();

    public List<Employee> getEmployees(Filter filter) {
        try {
            String webEndPoint = "<ENDPOINT URL FOR GETTING EMPLOYEE DATA>";
            RestTemplate restTemplate = new RestTemplate();
            ObjectNode request = mapper.createObjectNode();
            ArrayNode locations = mapper.createArrayNode();
            locations.add("Noida");
            locations.add("Gurgaon");
            request.set("location", locations);

            ResponseEntity<ArrayNode> responseEntity = restTemplate.postForEntity(webEndPoint, request, ArrayNode.class);
            ArrayNode jsonArray = responseEntity.getBody();
            List<Employee> employees = new ArrayList<Employee>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonNode jsonNode = jsonArray.get(i);
                Employee employee = new Employee();
                employee.setEMAIL(jsonNode.get("EMAIL").asText());
                employee.setNAME(jsonNode.get("NAME").asText());
                employee.setIMGURL(jsonNode.get("IMGURL").asText());
                employee.setSUBJECT(jsonNode.get("SUBJECT").asText());
                employees.add(employee);
            }
            return employees;

        } catch (Exception e) {
            throw new BWisherException("Pivot Service Exception occured.", e);
        }
    }

}
