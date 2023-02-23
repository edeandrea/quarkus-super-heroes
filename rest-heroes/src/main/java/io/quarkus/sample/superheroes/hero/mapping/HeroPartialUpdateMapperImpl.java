package io.quarkus.sample.superheroes.hero.mapping;

import javax.annotation.processing.Generated;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.sample.superheroes.hero.Hero;

/**
 * This is a temporary fix until https://github.com/mapstruct/mapstruct/issues/2950 is released in a Mapstruct release
 */

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T14:35:50-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@ApplicationScoped
public class HeroPartialUpdateMapperImpl implements HeroPartialUpdateMapper {

    @Override
    public void mapPartialUpdate(Hero input, Hero target) {
        if ( input == null ) {
            return;
        }

        if ( input.getId() != null ) {
            target.setId( input.getId() );
        }
        if ( input.getName() != null ) {
            target.setName( input.getName() );
        }
        if ( input.getOtherName() != null ) {
            target.setOtherName( input.getOtherName() );
        }
        if ( input.getLevel() != null ) {
            target.setLevel( input.getLevel() );
        }
        if ( input.getPicture() != null ) {
            target.setPicture( input.getPicture() );
        }
        if ( input.getPowers() != null ) {
            target.setPowers( input.getPowers() );
        }
    }
}
