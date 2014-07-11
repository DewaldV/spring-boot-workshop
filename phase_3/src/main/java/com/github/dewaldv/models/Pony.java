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

    public Pony() {
    }

    public Pony(String name, PonyType type) {
        this.name = name;
        this.type = type;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (!(o instanceof Pony)) {
            return false;

        }

        Pony p = (Pony) o;
        return p.getId() == this.getId()
                && this.getName().equals(p.getName())
                && this.getType().equals(p.getType());
    }

    @Override
    public int hashCode() {
        return (5 * this.getId()) + (7 * this.getName().hashCode()) + (11 * this.getType().hashCode());
    }
}
