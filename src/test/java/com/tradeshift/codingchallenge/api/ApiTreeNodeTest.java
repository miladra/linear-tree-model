package com.tradeshift.codingchallenge.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient(registerRestTemplate = true)
public class ApiTreeNodeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    public void getNotExistTreeNode() {

            String searchNode = "notExistNode";
            String saveUrl = "http://localhost:" + serverPort + "/rest/treeNode/v1/" + searchNode;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<?> request = new HttpEntity<>(headers);
            ResponseEntity<Object> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, Object.class);

            assertThat(result.getStatusCode())
                    .as("GET API Node")
                    .isEqualTo(HttpStatus.NOT_FOUND);

            LinkedHashMap<String, String> body = (LinkedHashMap<String, String>) result.getBody();
            assertThat(body.get("message")).isEqualTo("Resource not found: " + searchNode);
    }

    @Test
    public void getAllNodeTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/treeNode/v1/A";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, Object.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

        ArrayList<String> resultBody = (ArrayList<String>)result.getBody();

        assertThat(resultBody.size())
                .as("Check size")
                .isEqualTo(10);
    }

    @Test
    public void addTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/treeNode/v1/add";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("afterNode" , 'E');
        parameters.put("newNode" ,'K');

        HttpEntity<Map> request = new HttpEntity<Map>(parameters, headers);
        ResponseEntity<String> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, String.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

        String resultBody = result.getBody();


        assertThat(resultBody)
                .as("Check size")
                .isEqualTo("Node added");
    }
}