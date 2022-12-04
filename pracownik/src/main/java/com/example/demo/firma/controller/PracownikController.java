package com.example.demo.firma.controller;

import com.example.demo.firma.dto.GetPracownikResponse;
import com.example.demo.firma.dto.GetPracowniksResponse;
import com.example.demo.firma.dto.PostPracownikRequest;
import com.example.demo.firma.dto.PutPracownikRequest;
import com.example.demo.firma.entity.Pracownik;
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
@RequestMapping("api/pracowniks")
public class PracownikController {
    private SzefService szefService;
    private PracownikService pracownikService;

    @Autowired
    public PracownikController(SzefService szefService, PracownikService pracownikService){
        this.szefService = szefService;
        this.pracownikService = pracownikService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})//When empty JSON is default
    public ResponseEntity<GetPracowniksResponse> getCharacters() {
        return ResponseEntity.ok(GetPracowniksResponse.entityToDtoMapper().apply(pracownikService.findAll()));
    }
    @GetMapping("{id}")
    public ResponseEntity<GetPracownikResponse> getCharacter(@PathVariable("id") int id) {
        return  pracownikService.find(id)
                .map(value -> ResponseEntity.ok(GetPracownikResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Void> postPracownik(@RequestBody PostPracownikRequest request, UriComponentsBuilder builder) {
        Pracownik pracownik = PostPracownikRequest
                .dtoToEntityMapper( () -> null)
                .apply(request);
        pracownik = pracownikService.save(pracownik);
        return ResponseEntity.created(builder.pathSegment("api", "pracowniks", "{id}")
                .buildAndExpand(pracownik.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePracownik(@PathVariable("id") int id) {
        Optional<Pracownik> pracownik = pracownikService.find(id);
        if (pracownik.isPresent()) {
            pracownikService.delete(pracownik.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> putPracownik(@RequestBody PutPracownikRequest request, @PathVariable("id") int id) {
        Optional<Pracownik> pracownik = pracownikService.find(id);
        if (pracownik.isPresent()) {
            PutPracownikRequest.dtoToEntityUpdater().apply(pracownik.get(), request);
            pracownikService.update(pracownik.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
