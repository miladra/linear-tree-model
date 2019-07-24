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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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
        String saveUrl = "http://localhost:" + serverPort + "/rest/treeNode/" + searchNode;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, Object.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        LinkedHashMap<String , String> body = (LinkedHashMap<String , String>)result.getBody();
        assertThat(body.get("message")).isEqualTo("Resource not found: " + searchNode);

    }

    @Test
    public void getAllNodeTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/treeNode/root";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<Object> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, Object.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

        ArrayList<String> resultBody = (ArrayList<String>)result.getBody();

        assertThat(resultBody.size())
                .as("Check size")
                .isEqualTo(10);
    }

    @Test
    public void addTreeNode() {
    }
}