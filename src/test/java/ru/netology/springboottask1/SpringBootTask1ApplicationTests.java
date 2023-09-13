package ru.netology.springboottask1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootTask1ApplicationTests {

    private static final GenericContainer<?> devApp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devAppTest() {
        int port = devApp.getMappedPort(8080);
        String expected = "Current profile is dev";
        String actual = invokeEndpoint(port);
        //System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void prodAppTest() {
        int port = prodApp.getMappedPort(8081);
        String expected = "Current profile is production";
        String actual = invokeEndpoint(port);
        //System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    private String invokeEndpoint(int port) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + port + "/profile", String.class);
        return forEntity != null ? forEntity.getBody() : "";
    }

}
