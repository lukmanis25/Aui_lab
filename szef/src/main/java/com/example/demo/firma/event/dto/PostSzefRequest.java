package com.example.demo.firma.event.dto;

import com.example.demo.firma.entity.Szef;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostSzefRequest {
    /**
     * User's login.
     */
    private int id;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Szef, PostSzefRequest> entityToDtoMapper() {
        return entity -> PostSzefRequest.builder()
                .id(entity.getId())
                .build();
    }
}
