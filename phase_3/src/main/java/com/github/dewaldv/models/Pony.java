package com.github.dewaldv.models;

import javax.persistence.*;

@Entity
public class Pony {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    @Enumerated
    private PonyType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
