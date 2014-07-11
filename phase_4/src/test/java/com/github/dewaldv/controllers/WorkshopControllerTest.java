package com.github.dewaldv.controllers;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkshopControllerTest {
    @Test
    public void shouldSayHelloWorld() throws Exception {
        WorkshopController workshopController = new WorkshopController();

        String result = workshopController.get();

        assertThat(result, equalTo("Hello World!"));
    }
}