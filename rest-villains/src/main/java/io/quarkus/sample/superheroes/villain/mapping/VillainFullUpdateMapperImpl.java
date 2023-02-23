package io.quarkus.sample.superheroes.villain.mapping;

import javax.annotation.processing.Generated;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.sample.superheroes.villain.Villain;

/**
 * This is a temporary fix until https://github.com/mapstruct/mapstruct/issues/2950 is released in a Mapstruct release
 */

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T14:32:10-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@ApplicationScoped
public class VillainFullUpdateMapperImpl implements VillainFullUpdateMapper {

    @Override
    public void mapFullUpdate(Villain input, Villain target) {
        if ( input == null ) {
            return;
        }

        target.name = input.name;
        target.otherName = input.otherName;
        target.level = input.level;
        target.picture = input.picture;
        target.powers = input.powers;
    }
}
