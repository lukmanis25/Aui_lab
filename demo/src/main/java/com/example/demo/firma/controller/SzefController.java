package com.example.demo.firma.controller;

import com.example.demo.firma.dto.GetPracownikResponse;
import com.example.demo.firma.dto.GetPracowniksResponse;
import com.example.demo.firma.dto.GetSzefResponse;
import com.example.demo.firma.dto.GetSzefsResponse;
import com.example.demo.firma.entity.Szef;
import com.example.demo.firma.service.PracownikService;
import com.example.demo.firma.service.SzefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetSzefsResponse> getSzefs() {
//        return ResponseEntity.ok(GetCharactersResponse.entityToDtoMapper().apply(characterService.findAll()));
        List<Szef> all = szefService.findAll();
        Function<Collection<Szef>, GetSzefsResponse> mapper = GetSzefsResponse.entityToDtoMapper();
        GetSzefsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{szef_id}")
    public ResponseEntity<GetSzefResponse> getSzef(@PathVariable("szef_id") int szef_id) {
        return szefService.find(szef_id)
                .map(value -> ResponseEntity
                        .ok(GetSzefResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
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
}

