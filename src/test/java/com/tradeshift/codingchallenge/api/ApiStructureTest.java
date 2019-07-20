package com.tradeshift.codingchallenge.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiStructureTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getRestStructure() {
        /*
        String saveUrl = "http://localhost:" + serverPort + "/flowable-modeler/api/rest/saveSequences";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorization("test-rest", "test"));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity<Sequence> request = new HttpEntity<Sequence>(sequence, headers);

        ResponseEntity<Sequence> result = restTemplate.exchange(saveUrl, HttpMethod.POST, request, Sequence.class);
        Sequence resultSequence = (Sequence)result.getBody();

        assertThat(result.getStatusCode())
                .as("GET API Editor sequences")
                .isEqualTo(HttpStatus.OK);

        assertThat(resultSequence.getId())
                .as("Check sequences Id")
                .isNotNull();
    */
    }

}