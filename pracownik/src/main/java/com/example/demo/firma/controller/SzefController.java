package com.example.demo.firma.controller;

import com.example.demo.firma.dto.*;
import com.example.demo.firma.entity.Pracownik;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.service.PracownikService;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/szefs")
public class SzefController {

    private SzefService szefService;
    private PracownikService pracownikService;

    @Autowired
    public SzefController(SzefService szefService, PracownikService pracownikService){
        this.szefService = szefService;
        this.pracownikService = pracownikService;
    }

    @GetMapping("{szef_id}/pracowniks")
    public ResponseEntity<GetPracowniksResponse> getPracowniks(@PathVariable("szef_id") int szef_id) {
        Optional<Szef> szef = szefService.find(szef_id);
        return szef.map(value -> ResponseEntity.ok(GetPracowniksResponse.entityToDtoMapper().apply(pracownikService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{szef_id}/pracowniks/{pracownik_id}")
    public ResponseEntity<GetPracownikResponse> getPracownik(@PathVariable("szef_id") int szef_id,
                                                             @PathVariable("pracownik_id") int pracownik_id) {
        return pracownikService.find(szef_id, pracownik_id)
                .map(value -> ResponseEntity.ok(GetPracownikResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("{szef_id}")
    public ResponseEntity<Void> deleteSzef(@PathVariable("szef_id") int szef_id) {
        Optional<Szef> szef = szefService.find(szef_id);
        if (szef.isPresent()) {
            szefService.delete(szef.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Void> postSzef(@RequestBody PostSzefRequest request, UriComponentsBuilder builder) {
        Szef szef = PostSzefRequest.dtoToEntityMapper().apply(request);
        szefService.save(szef);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "szefs", "{szef_id}")
                        .buildAndExpand(szef.getId()).toUri())
                .build();
    }


    @PostMapping("{szef_id}/pracowniks")
    public ResponseEntity<Void> postPracownik(@PathVariable("szef_id") int szef_id,
                                              @RequestBody PostPracownikRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Szef> szef = szefService.find(szef_id);
        if (szef.isPresent()) {
            Pracownik pracownik = PostPracownikRequest
                    .dtoToEntityMapper( szef::get)
                    .apply(request);
            pracownik = pracownikService.save(pracownik);
            return ResponseEntity.created(builder.pathSegment("api", "szefs", "{szef_id}", "pracowniks", "{id}")
                    .buildAndExpand(szef.get().getId(), pracownik.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{szef_id}/pracowniks/{id}")
    public ResponseEntity<Void> deletePracownik(@PathVariable("szef_id") int szef_id,
                                                @PathVariable("id") int id) {
        Optional<Pracownik> pracownik = pracownikService.find(szef_id, id);
        if (pracownik.isPresent()) {
            pracownikService.delete(pracownik.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{szef_id}/pracowniks/{id}")
    public ResponseEntity<Void> putPracownik(@PathVariable("szef_id") int szef_id,
                                             @RequestBody PutPracownikRequest request,
                                             @PathVariable("id") int id) {
        Optional<Pracownik> pracownik = pracownikService.find(szef_id, id);
        if (pracownik.isPresent()) {
            PutPracownikRequest.dtoToEntityUpdater().apply(pracownik.get(), request);
            pracownikService.update(pracownik.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

