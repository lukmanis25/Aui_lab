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
        Szef tomasz = new Szef(1, "Tomasz");
        Szef adam = new Szef(2, "Adam");
        Szef michal = new Szef(3, "Michał");

        szefService.create(tomasz);
        szefService.create(adam);
        szefService.create(michal);

        pracownikService.create(new Pracownik(1, "Krzyś", tomasz));
        pracownikService.create(new Pracownik(2, "Bartek", tomasz));
        pracownikService.create(new Pracownik(3, "Ania", adam));
        pracownikService.create(new Pracownik(4, "Kuba", adam));
        pracownikService.create(new Pracownik(5, "Natalia", michal));
        pracownikService.create(new Pracownik(6, "Łukasz", michal));


    }
}
