package org.foody.service;

import org.foody.domain.Review;
import org.foody.service.dto.ComentariuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ComentariuService {

    ComentariuDTO save(ComentariuDTO comentariuDTO);

    Page<ComentariuDTO> findAll(Pageable pageable);

    ComentariuDTO findOne(Long id);

    void delete(Long id);

    Page<ComentariuDTO> findAllByReview(Pageable pageable, Review review);
}
