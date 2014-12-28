package study.gordon.titan.showcase.simple.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import study.gordon.titan.showcase.simple.entity.Gender;
import study.gordon.titan.showcase.simple.entity.SimpleEntity;

public class SimpleEntityRestTest {

    private static final String BASE_URL = "http://localhost:8080/titan/api/v1/simple";

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();

        // create
        SimpleEntity simple = new SimpleEntity();
        simple.setAge(27);
        simple.setName("Goodyear");
        URI resourceUri = restTemplate.postForLocation(BASE_URL, simple);
        SimpleEntity createdSimple = restTemplate.getForObject(resourceUri, SimpleEntity.class);
        assertEquals(createdSimple.getName(), simple.getName());

        // update
        createdSimple.setGender(Gender.FEMALE);
        createdSimple.setShow(true);
        restTemplate.put(resourceUri, createdSimple);
        SimpleEntity updatedSimple = restTemplate.getForObject(resourceUri, SimpleEntity.class);
        assertEquals(createdSimple.getGender(), updatedSimple.getGender());
        assertEquals(createdSimple.getShow(), updatedSimple.getShow());

        // delete
        restTemplate.delete(resourceUri);
        try {
            restTemplate.getForObject(resourceUri, SimpleEntity.class);
            fail("Get should fail while feth a deleted task");
        } catch (HttpStatusCodeException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
