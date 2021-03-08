package com.example.webservicelab.entities;

import javax.persistence.*;

@Entity
@Table(name="Dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String breed;

    public Dog(long id, String name, String breed){
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    public Dog() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}

