package com.example.demo.firma.entity;

import java.util.ArrayList;
import java.util.List;

public class Szef {
    private int id;
    private String imie;
    public List<Pracownik> pracownicy=new ArrayList<Pracownik>();

    public Szef(int id, String imie) {
        this.id = id;
        this.imie = imie;
    }


    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    @Override
    public String toString() {
        return "Szef(id = " + this.id + ", imie = " + this.imie + ")";
    }
}
