package com.github.dewaldv.controllers;

import com.github.dewaldv.models.Pony;
import com.github.dewaldv.models.PonyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PonyController {

    private PonyRepository ponyRepository;

    @Autowired
    public PonyController(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    @RequestMapping(value = "/ponies", produces = "application/json")
    public Iterable<Pony> getAll() {
        return ponyRepository.findAll();
    }

    @RequestMapping(value = "/ponies", consumes = "application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPony(@RequestBody Pony pony) {
        ponyRepository.save(pony);
    }

    @RequestMapping(value = "/ponies/{name}", produces = "application/json")
    public Pony getPony(@PathVariable String name) {
        return ponyRepository.findByName(name);
    }
}
