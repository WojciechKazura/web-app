package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Samoloty")
public class Samolot {

    String nazwa;
    String model;
    int iloscMiejscNaPokladzie;
    @Id
    Long id;

    public Long getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getModel() {
        return model;
    }

    public int getIloscMiejscNaPokladzie() {
        return iloscMiejscNaPokladzie;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setIloscMiejscNaPokladzie(int iloscMiejscNaPokladzie) {
        this.iloscMiejscNaPokladzie = iloscMiejscNaPokladzie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Samolot{" +
                "nazwa='" + nazwa + '\'' +
                ", model='" + model + '\'' +
                ", iloscMiejscNaPokladzie=" + iloscMiejscNaPokladzie +
                ", id=" + id +
                '}';
    }
}
