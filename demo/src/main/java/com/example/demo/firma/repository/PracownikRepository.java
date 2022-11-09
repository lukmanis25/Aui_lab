package com.example.demo.firma.repository;


import com.example.demo.Storage;
import com.example.demo.firma.entity.Pracownik;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class PracownikRepository {

    private final Storage store;

    @Autowired
    public PracownikRepository(Storage store) {
        this.store = store;
    }

    public void save(Pracownik pracownik) {store.pracownicy.add(pracownik);}

    public Optional<Pracownik> findById(long id) {
        for(Pracownik pracownik : store.pracownicy){
            if (pracownik.getId() == id){
                return Optional.of(pracownik);
            }
        }
        return Optional.empty();
    }


    public List<Pracownik> findAll() { return store.pracownicy; }

    public void del(Long id){
        for(Pracownik pracownik : store.pracownicy){
            if (pracownik.getId() == id){
                pracownik.getSzef().pracownicy.remove(pracownik);
                store.pracownicy.remove(pracownik);
                return;
            }
        }
        System.out.println("Brak takiego pracownika");

    }


}
