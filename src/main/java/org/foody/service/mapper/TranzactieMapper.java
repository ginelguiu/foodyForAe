package org.foody.service.mapper;

import org.foody.domain.*;
import org.foody.service.dto.TranzactieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tranzactie and its DTO TranzactieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TranzactieMapper extends EntityMapper<TranzactieDTO, Tranzactie> {



    default Tranzactie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tranzactie tranzactie = new Tranzactie();
        tranzactie.setId(id);
        return tranzactie;
    }
}
