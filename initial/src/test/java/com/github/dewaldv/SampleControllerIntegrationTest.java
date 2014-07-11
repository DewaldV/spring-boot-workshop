package com.github.dewaldv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleController.class)
public class SampleControllerIntegrationTest {

    @Autowired
    private SampleController sampleController;

    @Test
    public void shouldStartContextAndReturnHelloWorld() throws Exception {
        String result = sampleController.get();

        assertThat(result, equalTo("Hello World!"));
    }
}
