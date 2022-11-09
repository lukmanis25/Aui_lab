package com.example.demo.firma.repository;

import com.example.demo.Storage;
import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class SzefRepository {

    private final Storage store;

    @Autowired
    public SzefRepository(Storage store) {
        this.store = store;
    }

    public void save(Szef szef) {store.szefowie.add(szef);}

    public Optional<Szef> findById(long id) {
        for(Szef szef : store.szefowie){
            if (szef.getId() == id){
                return Optional.of(szef);
            }
        }
        return Optional.empty();
    }


    public List<Szef> findAll() { return store.szefowie; }

    public void del(Long id) {
        for (Szef szef : store.szefowie) {
            if (szef.getId() == id) {
                for(Pracownik pracownik : szef.pracownicy){
                    pracownik.setSzef(null);
                }
                store.szefowie.remove(szef);
                return;
            }
        }
        System.out.println("Brak takiego szefa");

    }

}