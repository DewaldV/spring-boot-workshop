package com.github.dewaldv;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class SampleControllerTest {
    @Test
    public void shouldReturnHelloWorld() throws Exception {
        SampleController sampleController = new SampleController();

        String result = sampleController.get();

        assertThat(result, equalTo("Hello World!"));
    }
}