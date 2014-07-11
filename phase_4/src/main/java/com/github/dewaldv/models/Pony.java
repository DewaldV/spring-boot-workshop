package com.github.dewaldv.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Pony {

    @Id
    @Column
    private String name;

    @Column
    @Enumerated
    private PonyType type;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pony> friends;

    public Pony() {
    }

    public Pony(String name, PonyType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PonyType getType() {
        return type;
    }

    public void setType(PonyType type) {
        this.type = type;
    }

    public Set<Pony> getFriends() {
        return friends;
    }

    public void setFriends(Set<Pony> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Pony)) {
            return false;

        }

        Pony p = (Pony) o;
        return this.getName().equals(p.getName())
                && this.getType().equals(p.getType());
    }

    @Override
    public int hashCode() {
        return (7 * this.getName().hashCode()) + (11 * this.getType().hashCode());
    }
}
