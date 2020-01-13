package org.foody.service;

import org.foody.domain.Categorie;
import org.foody.domain.User;
import org.foody.service.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ReviewService {

    ReviewDTO save(ReviewDTO reviewDTO);

    Page<ReviewDTO> findAllByCategorie(Pageable pageable, Categorie categorie);

    Page<ReviewDTO> findAllByUser(Pageable pageable, User user);

    Page<ReviewDTO> findAll(Pageable pageable);

    ReviewDTO findOne(Long id);

    void delete(Long id);
}
