package com.github.dewaldv.controllers;

import com.github.dewaldv.WorkshopConfiguration;
import com.github.dewaldv.models.Pony;
import com.github.dewaldv.models.PonyRepository;
import com.github.dewaldv.models.PonyType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasProperty;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WorkshopConfiguration.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PonyRepositoryIntegrationTest {

    @Autowired
    private PonyRepository ponyRepository;

    @Before
    public void setup() throws Exception {
        ponyRepository.deleteAll();
    }

    @Test
    public void shouldSaveAndFetchPony() throws Exception {
        Pony expectedPony = new Pony();
        expectedPony.setName("Lyra");
        expectedPony.setType(PonyType.UNICORN);

        ponyRepository.save(expectedPony);
        Pony resultingPony = ponyRepository.findByName("Lyra");

        assertThat(resultingPony, equalTo(expectedPony));
    }
}
