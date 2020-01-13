package org.foody.service.impl;

import org.foody.service.TranzactieService;
import org.foody.domain.Tranzactie;
import org.foody.repository.TranzactieRepository;
import org.foody.service.dto.TranzactieDTO;
import org.foody.service.mapper.TranzactieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Tranzactie.
 */
@Service
@Transactional
public class TranzactieServiceImpl implements TranzactieService {

    private final Logger log = LoggerFactory.getLogger(TranzactieServiceImpl.class);

    private final TranzactieRepository tranzactieRepository;

    private final TranzactieMapper tranzactieMapper;

    public TranzactieServiceImpl(TranzactieRepository tranzactieRepository, TranzactieMapper tranzactieMapper) {
        this.tranzactieRepository = tranzactieRepository;
        this.tranzactieMapper = tranzactieMapper;
    }

    /**
     * Save a tranzactie.
     *
     * @param tranzactieDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TranzactieDTO save(TranzactieDTO tranzactieDTO) {
        log.debug("Request to save Tranzactie : {}", tranzactieDTO);
        Tranzactie tranzactie = tranzactieMapper.toEntity(tranzactieDTO);
        tranzactie = tranzactieRepository.save(tranzactie);
        return tranzactieMapper.toDto(tranzactie);
    }

    /**
     * Get all the tranzacties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TranzactieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tranzacties");
        return tranzactieRepository.findAll(pageable)
            .map(tranzactieMapper::toDto);
    }

    /**
     * Get one tranzactie by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TranzactieDTO findOne(Long id) {
        log.debug("Request to get Tranzactie : {}", id);
        Tranzactie tranzactie = tranzactieRepository.findOne(id);
        return tranzactieMapper.toDto(tranzactie);
    }

    /**
     * Delete the tranzactie by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tranzactie : {}", id);
        tranzactieRepository.delete(id);
    }
}
