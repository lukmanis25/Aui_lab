package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Pracownik;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPracowniksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class PracownikEntry {

        private int id;

        private String imie;

    }


    @Singular
    private List<PracownikEntry> pracowniks;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<com.example.demo.firma.entity.Pracownik>, GetPracowniksResponse> entityToDtoMapper() {
        return pracownicy -> {
            GetPracowniksResponseBuilder response = GetPracowniksResponse.builder();
            pracownicy.stream()
                    .map(pracownik -> PracownikEntry.builder()
                            .id(pracownik.getId())
                            .imie(pracownik.getImie())
                            .build())
                    .forEach(response::pracownik);
            return response.build();
        };
    }
}
