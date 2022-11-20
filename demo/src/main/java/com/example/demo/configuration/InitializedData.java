package com.example.demo.configuration;

import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.service.PracownikService;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {
    private final PracownikService pracownikService;

    /**
     * Service for users operations.
     */
    private final SzefService szefService;

    @Autowired
    public InitializedData(PracownikService  pracownikService, SzefService szefService) {
        this.pracownikService  = pracownikService;
        this.szefService = szefService;
    }

    @PostConstruct
    private synchronized void init() {


        Szef tomasz = Szef.builder()
                .id(1)
                .imie("Tomasz")
                .build();
        Szef adam = Szef.builder()
                .id(2)
                .imie("Adam")
                .build();
        Szef michal = Szef.builder()
                .id(3)
                .imie("Michal")
                .build();

        szefService.save(tomasz);
        szefService.save(adam);
        szefService.save(michal);

        Pracownik szymon = Pracownik.builder()
                .id(1)
                .imie("Szymon")
                .szef(michal)
                .build();
        Pracownik natalia = Pracownik.builder()
                .id(2)
                .imie("Natalia")
                .szef(michal)
                .build();
        Pracownik piotr = Pracownik.builder()
                .id(3)
                .imie("Piotr")
                .szef(adam)
                .build();
        Pracownik patryk = Pracownik.builder()
                .id(4)
                .imie("Patryk")
                .szef(adam)
                .build();
        Pracownik krzys = Pracownik.builder()
                .id(5)
                .imie("Krzyś")
                .szef(tomasz)
                .build();
        Pracownik bartek = Pracownik.builder()
                .id(6)
                .imie("Bartek")
                .szef(tomasz)
                .build();

        pracownikService.save(szymon);
        pracownikService.save(natalia);
        pracownikService.save(patryk);
        pracownikService.save(piotr);
        pracownikService.save(krzys);
        pracownikService.save(bartek);

    }
}
