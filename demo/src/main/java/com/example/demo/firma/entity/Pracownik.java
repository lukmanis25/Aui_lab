package com.example.demo.firma.entity;

public class Pracownik {
    private int id;
    private String imie;
    private Szef szef;

    public Pracownik(int id, String imie, Szef szef) {
        this.id = id;
        this.imie = imie;
        this.szef = szef;
        szef.pracownicy.add(this);
    }


    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public Szef getSzef() {
        return szef;
    }

    public void setSzef(Szef szef) {
        this.szef = szef;
    }

    @Override
    public String toString() {
        return "Pracownik(id = " + this.id + ", imie = " + this.imie + ")";
    }
}
