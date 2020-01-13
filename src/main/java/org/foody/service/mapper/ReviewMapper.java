package org.foody.service.mapper;

import org.foody.domain.*;
import org.foody.service.dto.ReviewDTO;

import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategorieMapper.class})
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "categorie.id", target = "categorieId")
    ReviewDTO toDto(Review review);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "categorieId", target = "categorie")
    Review toEntity(ReviewDTO reviewDTO);

    default Review fromId(Long id) {
        if (id == null) {
            return null;
        }
        Review review = new Review();
        review.setId(id);
        return review;
    }
}
