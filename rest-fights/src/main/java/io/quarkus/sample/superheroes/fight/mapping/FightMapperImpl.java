package io.quarkus.sample.superheroes.fight.mapping;

import javax.annotation.processing.Generated;
import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.sample.superheroes.fight.schema.Fight;

/**
 * This is a temporary fix until https://github.com/mapstruct/mapstruct/issues/2950 is released in a Mapstruct release
 */
@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-23T14:32:10-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@ApplicationScoped
public class FightMapperImpl implements FightMapper {

    @Override
    public Fight toSchema(io.quarkus.sample.superheroes.fight.Fight fight) {
        if ( fight == null ) {
            return null;
        }

        Fight.Builder fight1 = Fight.newBuilder();

        fight1.setId( toString( fight.id ) );
        fight1.setFightDate( fight.fightDate );
        fight1.setWinnerName( fight.winnerName );
        if ( fight.winnerLevel != null ) {
            fight1.setWinnerLevel( fight.winnerLevel );
        }
        fight1.setWinnerPicture( fight.winnerPicture );
        fight1.setLoserName( fight.loserName );
        if ( fight.loserLevel != null ) {
            fight1.setLoserLevel( fight.loserLevel );
        }
        fight1.setLoserPicture( fight.loserPicture );
        fight1.setWinnerTeam( fight.winnerTeam );
        fight1.setLoserTeam( fight.loserTeam );

        return fight1.build();
    }
}
