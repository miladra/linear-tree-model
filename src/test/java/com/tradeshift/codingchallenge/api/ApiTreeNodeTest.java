package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.entity.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
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
            String saveUrl = "http://localhost:" + serverPort + "/rest/v1/treeNode/" + searchNode;
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

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/treeNode/A";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<List<TreeNode>> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, new ParameterizedTypeReference<List<TreeNode>>(){});

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

        List<TreeNode> resultBody = result.getBody();

        assertThat(resultBody.size())
                .as("Check size")
                .isEqualTo(10);
    }

    @Test
    public void updateTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/treeNode/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("newParentPosition" , 'C');
        parameters.put("currentNode" ,'E');

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

    @Test
    public void updateRootTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/treeNode/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("newParentPosition" , 'A');
        parameters.put("currentNode" ,'E');

        HttpEntity<Map> request = new HttpEntity<Map>(parameters, headers);
        ResponseEntity<String> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, String.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void updateNodeWithChildrenTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/treeNode/update";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("newParentPosition" , 'C');
        parameters.put("currentNode" ,'B');

        HttpEntity<Map> request = new HttpEntity<Map>(parameters, headers);
        ResponseEntity<String> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, String.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.BAD_REQUEST);

    }
}