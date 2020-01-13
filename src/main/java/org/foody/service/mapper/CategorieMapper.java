package org.foody.service.mapper;

import org.foody.domain.*;
import org.foody.service.dto.CategorieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Categorie and its DTO CategorieDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategorieMapper extends EntityMapper<CategorieDTO, Categorie> {



    default Categorie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(id);
        return categorie;
    }
}
