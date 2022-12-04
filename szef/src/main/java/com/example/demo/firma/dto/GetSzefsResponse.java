package com.example.demo.firma.dto;

import com.example.demo.firma.entity.Szef;
import lombok.*;

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
public class GetSzefsResponse {
    @Singular
    private List<Integer> szefs;

    public static Function<Collection<Szef>, GetSzefsResponse> entityToDtoMapper() {
        return pracownicy -> {
            GetSzefsResponseBuilder response = GetSzefsResponse.builder();
            pracownicy.stream()
                    .map(Szef::getId)
                    .forEach(response::szef);
            return response.build();
        };
    }
}
