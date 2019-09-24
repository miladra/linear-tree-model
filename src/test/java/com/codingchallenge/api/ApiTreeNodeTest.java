package com.codingchallenge.api;

import com.codingchallenge.domain.TreeModel;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.h2.console.enabled=true")
@AutoConfigureWebClient(registerRestTemplate = true)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTreeNodeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    public void step1_AddRootNode() {

        //Create
        //   A
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "0");
        params.put("addAsChild", "false");

        TreeModel node = new TreeModel();
        node.setName("A");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<TreeModel>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step2_AddChildNode() {
        // Create
        //   A
        //   |
        //   B
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "A");
        params.put("addAsChild", "true");

        TreeModel node = new TreeModel();
        node.setName("B");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<TreeModel>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step3_AddChildNode2() {
        // Create
        //   A
        //  / \
        //  B  C
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "B");
        params.put("addAsChild", "false");

        TreeModel node = new TreeModel();
        node.setName("C");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step4_AddChildNode3() {
        // Create
        //     B
        //  /  |  \
        //  B  C   D
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "C");
        params.put("addAsChild", "false");

        TreeModel node = new TreeModel();
        node.setName("D");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step5_AddChildNode4() {
        // Create
        //     B
        //  /  |  \
        //  B  C   D
        //     |
        //     E
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "C");
        params.put("addAsChild", "true");

        TreeModel node = new TreeModel();
        node.setName("E");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step6_AddChildNode5() {
        // Create
        //     B
        //  /  |  \
        //  B  C   D
        //     |   |
        //     E   F
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "D");
        params.put("addAsChild", "true");

        TreeModel node = new TreeModel();
        node.setName("F");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<TreeModel>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step7_AddChildNode6() {
        // Create
        //     B
        //  /  |  \
        //  B  C    D
        //     |   / \
        //     E   F  G
        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{addAsChild}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("newPosition", "F");
        params.put("addAsChild", "false");

        TreeModel node = new TreeModel();
        node.setName("G");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<TreeModel>(node, headers);

        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, TreeModel.class , params);



        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void step8_GetAllNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<List<TreeModel>> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, new ParameterizedTypeReference<List<TreeModel>>(){});

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

        List<TreeModel> resultBody = result.getBody();

        assertThat(resultBody.size())
                .as("Check size")
                .isEqualTo(7);
    }

    @Test
    public void step9_GetSubTree() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{name}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String > params = new HashMap <> ();
        params.put("name", "A");

        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<List<TreeModel>> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, new ParameterizedTypeReference<List<TreeModel>>(){} , params);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

        List<TreeModel> resultBody = result.getBody();

        assertThat(resultBody.size())
                .as("Check size")
                .isEqualTo(7);
    }

    @Test
    public void stepA_GetNotExistTreeNode() {

            String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{name}";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

            Map<String, String > params = new HashMap <> ();
            params.put("name", "QW");

            HttpEntity<?> request = new HttpEntity<>(headers);
            ResponseEntity<Object> result = restTemplate.exchange(saveUrl, HttpMethod.GET, request, Object.class , params);

            assertThat(result.getStatusCode())
                    .as("GET API Node")
                    .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void stepB_MoveToParentWithChildren() {

        //    move       to
        //     B                   B
        //  /  |  \             /     \
        //  B  C    D           B        D
        //     |   / \                 / / \
        //     E   F  G               C  F  G
        //                            |
        //                            E


        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{currentNode}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("newPosition" , "F");
        parameters.put("currentNode" ,"E");

        HttpEntity<Map> request = new HttpEntity<Map>(headers);
        ResponseEntity<List<TreeModel>> result = restTemplate.exchange(saveUrl, HttpMethod.PATCH, null, new ParameterizedTypeReference<List<TreeModel>>(){} , parameters);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

    }

    @Test
    public void stepC_MoveToParentWithoutChildren() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{newPosition}/{currentNode}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        Map<String, String> parameters = new HashMap<>();
        parameters.put("newPosition" , "B");
        parameters.put("currentNode" ,"E");

        HttpEntity<Map> request = new HttpEntity<Map>( headers);
        ResponseEntity<List<TreeModel>> result = restTemplate.exchange(saveUrl, HttpMethod.PATCH, null,  new ParameterizedTypeReference<List<TreeModel>>(){},parameters);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);
    }

    @Test
    public void stepD_DeleteRootTreeNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes/{id}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("id" , "7");

        HttpEntity<Map> request = new HttpEntity<Map>(headers);
        ResponseEntity<String> result = restTemplate.exchange(saveUrl, HttpMethod.DELETE, request,  String.class , parameters);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

    }

    @Test
    public void stepE_UpdateNameOfNode() {

        String saveUrl = "http://localhost:" + serverPort + "/rest/v1/nodes";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        TreeModel node = new TreeModel();
        node.setId(4);
        node.setName("K");
        node.setHeight(Long.valueOf(0));
        node.setRightNodeId(Long.valueOf(0));
        node.setLeftNodeId(Long.valueOf(0));
        node.setRootNode(null);
        node.setSubNodes(null);
        node.setSubRootNodes(null);
        node.setParentNode(null);

        HttpEntity<TreeModel> request = new HttpEntity<TreeModel>(node, headers);
        ResponseEntity<TreeModel> result = restTemplate.exchange(saveUrl, HttpMethod.PUT, request, TreeModel.class);

        assertThat(result.getStatusCode())
                .as("GET API Node")
                .isEqualTo(HttpStatus.OK);

    }
}