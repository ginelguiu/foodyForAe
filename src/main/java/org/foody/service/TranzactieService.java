package org.foody.service;

import org.foody.service.dto.TranzactieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Tranzactie.
 */
public interface TranzactieService {

    /**
     * Save a tranzactie.
     *
     * @param tranzactieDTO the entity to save
     * @return the persisted entity
     */
    TranzactieDTO save(TranzactieDTO tranzactieDTO);

    /**
     * Get all the tranzacties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TranzactieDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tranzactie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TranzactieDTO findOne(Long id);

    /**
     * Delete the "id" tranzactie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
