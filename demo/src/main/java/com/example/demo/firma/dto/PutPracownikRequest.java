package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Pracownik;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutPracownikRequest {
    private String imie;

    public static BiFunction<Pracownik, PutPracownikRequest, Pracownik> dtoToEntityUpdater() {
        return (pracownik, request) -> {
            pracownik.setImie(request.getImie());
            return pracownik;
        };
    }

}
