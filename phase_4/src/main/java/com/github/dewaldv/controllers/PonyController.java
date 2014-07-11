package com.github.dewaldv.controllers;

import com.github.dewaldv.models.Pony;
import com.github.dewaldv.models.PonyRepository;
import com.github.dewaldv.models.PonyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Resource<Pony>> getAll(@RequestParam(required = false) PonyType ponyType) {
        Iterable<Pony> ponies = ponyType != null
                ? ponyRepository.findByType(ponyType)
                : ponyRepository.findAll();

        return getPonyResources(ponies);
    }

    @RequestMapping(value = "{name}/friends", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Resource<String>> getFriends(@PathVariable String name) {
        Pony pony = ponyRepository.findByName(name);
        Iterable<Pony> friends = pony.getFriends();
        return getFriendResources(friends);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPony(@RequestBody Pony pony) {
        ponyRepository.save(pony);
    }

    @RequestMapping(value = "{name}/friends/{friendName}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFriendship(@PathVariable String name, @PathVariable String friendName) {
        Pony pony = ponyRepository.findByName(name);
        Pony friend = ponyRepository.findByName(friendName);

        pony.getFriends().add(friend);

        ponyRepository.save(pony);
    }

    @RequestMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
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
        return new Resource<>(pony,
                linkTo(PonyController.class).slash(pony.getName()).withSelfRel(),
                linkTo(PonyController.class).slash(pony.getName()).slash("friends").withRel("friends"),
                linkTo(PonyController.class).withRel("all"));
    }

    private Iterable<Resource<String>> getFriendResources(Iterable<Pony> ponies) {
        List<Resource<String>> friendsResources = new ArrayList<>();

        for (Pony pony : ponies) {
            friendsResources.add(getFriendResource(pony));
        }

        return friendsResources;
    }

    private Resource<String> getFriendResource(Pony pony) {
        return new Resource<>(pony.getName(),
                linkTo(PonyController.class).slash(pony.getName()).withSelfRel());
    }
}
