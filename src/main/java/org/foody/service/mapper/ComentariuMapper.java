package org.foody.service.mapper;

import org.foody.domain.*;
import org.foody.service.dto.ComentariuDTO;

import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class, UserMapper.class})
public interface ComentariuMapper extends EntityMapper<ComentariuDTO, Comentariu> {

    @Mapping(source = "review.id", target = "reviewId")
    @Mapping(source = "user.id", target = "userId")
    ComentariuDTO toDto(Comentariu comentariu);

    @Mapping(source = "reviewId", target = "review")
    @Mapping(source = "userId", target = "user")
    Comentariu toEntity(ComentariuDTO comentariuDTO);

    default Comentariu fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comentariu comentariu = new Comentariu();
        comentariu.setId(id);
        return comentariu;
    }
}
