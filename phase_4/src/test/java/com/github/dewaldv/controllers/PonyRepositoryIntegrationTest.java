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

    @Test
    public void shouldFindPoniesByType() throws Exception {
        Iterable<Pony> somePonies = new ArrayList<Pony>() {{
            add(new Pony("Bon Bon", PonyType.EARTH_PONY));
            add(new Pony("Bulk Biceps", PonyType.PEGASUS));
        }};
        ponyRepository.save(somePonies);


        Iterable<Pony> resultingPonies = ponyRepository.findByType(PonyType.PEGASUS);

        assertThat(resultingPonies, contains(
                hasProperty("name", equalTo("Bulk Biceps"))
        ));
    }
}
