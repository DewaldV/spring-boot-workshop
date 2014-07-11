package com.github.dewaldv.controllers;

import com.github.dewaldv.WorkshopConfiguration;
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
@SpringApplicationConfiguration(classes = WorkshopConfiguration.class)
public class SampleControllerIntegrationTest {

    @Autowired
    private WorkshopController workshopController;

    @Test
    public void shouldStartContextAndReturnHelloWorld() throws Exception {
        String result = workshopController.get();

        assertThat(result, equalTo("Hello World!"));
    }
}
