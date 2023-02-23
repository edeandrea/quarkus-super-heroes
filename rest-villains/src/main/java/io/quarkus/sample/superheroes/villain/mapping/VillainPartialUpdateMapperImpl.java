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
public class VillainPartialUpdateMapperImpl implements VillainPartialUpdateMapper {

    @Override
    public void mapPartialUpdate(Villain input, Villain target) {
        if ( input == null ) {
            return;
        }

        if ( input.id != null ) {
            target.id = input.id;
        }
        if ( input.name != null ) {
            target.name = input.name;
        }
        if ( input.otherName != null ) {
            target.otherName = input.otherName;
        }
        if ( input.level != null ) {
            target.level = input.level;
        }
        if ( input.picture != null ) {
            target.picture = input.picture;
        }
        if ( input.powers != null ) {
            target.powers = input.powers;
        }
    }
}
