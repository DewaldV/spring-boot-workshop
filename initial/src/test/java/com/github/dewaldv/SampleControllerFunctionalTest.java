package com.github.dewaldv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleController.class)
@IntegrationTest
@WebAppConfiguration
public class SampleControllerFunctionalTest {

    @Test
    public void shouldReturnHelloWorldOnDefaultEndpoint() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject("http://localhost:8080/", String.class);

        assertThat(result, equalTo("Hello World!"));
    }
}
