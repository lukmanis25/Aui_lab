package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Pracownik;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPracownikResponse {
    private int id;
    private String imie;

    public static Function<Pracownik, GetPracownikResponse> entityToDtoMapper() {
        return pracownik -> GetPracownikResponse.builder()
                .id(pracownik.getId())
                .imie(pracownik.getImie())
                .build();
    }
}
