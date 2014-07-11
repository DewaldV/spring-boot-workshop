package com.github.dewaldv.controllers;

import com.github.dewaldv.models.Pony;
import com.github.dewaldv.models.PonyRepository;
import com.github.dewaldv.models.PonyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping("/ponies")
public class PonyController {

    private PonyRepository ponyRepository;

    @Autowired
    public PonyController(PonyRepository ponyRepository) {
        this.ponyRepository = ponyRepository;
    }

    @RequestMapping(produces = "application/json")
    public Iterable<Resource<Pony>> getAll(@RequestParam(required = false)PonyType ponyType) {
        Iterable<Pony> ponies = ponyType != null
                ? ponyRepository.findByType(ponyType)
                : ponyRepository.findAll();

        return getPonyResources(ponies);
    }

    @RequestMapping(consumes = "application/json", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPony(@RequestBody Pony pony) {
        ponyRepository.save(pony);
    }

    @RequestMapping(value = "{name}", produces = "application/json")
    public Resource<Pony> getPony(@PathVariable String name) {
        Pony pony = ponyRepository.findByName(name);
        return getPonyResource(pony);
    }

    private Iterable<Resource<Pony>> getPonyResources(Iterable<Pony> ponies) {
        List<Resource<Pony>> ponyResources = new ArrayList<>();

        for (Pony pony : ponies) {
            ponyResources.add(getPonyResource(pony));
        }

        return ponyResources;
    }

    private Resource<Pony> getPonyResource(Pony pony) {
        Resource<Pony> ponyResource = new Resource<>(pony);
        ponyResource.add(linkTo(PonyController.class).slash(pony.getName()).withSelfRel());
        ponyResource.add(linkTo(PonyController.class).withRel("all"));
        return ponyResource;
    }
}
