package org.foody.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.foody.service.TranzactieService;
import org.foody.web.rest.errors.BadRequestAlertException;
import org.foody.web.rest.util.HeaderUtil;
import org.foody.web.rest.util.PaginationUtil;
import org.foody.service.dto.TranzactieDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Tranzactie.
 */
@RestController
@RequestMapping("/api")
public class TranzactieResource {

    private final Logger log = LoggerFactory.getLogger(TranzactieResource.class);

    private static final String ENTITY_NAME = "tranzactie";

    private final TranzactieService tranzactieService;

    public TranzactieResource(TranzactieService tranzactieService) {
        this.tranzactieService = tranzactieService;
    }

    /**
     * POST  /tranzacties : Create a new tranzactie.
     *
     * @param tranzactieDTO the tranzactieDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tranzactieDTO, or with status 400 (Bad Request) if the tranzactie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tranzacties")
    @Timed
    public ResponseEntity<TranzactieDTO> createTranzactie(@RequestBody TranzactieDTO tranzactieDTO) throws URISyntaxException {
        log.debug("REST request to save Tranzactie : {}", tranzactieDTO);
        if (tranzactieDTO.getId() != null) {
            throw new BadRequestAlertException("A new tranzactie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TranzactieDTO result = tranzactieService.save(tranzactieDTO);
        return ResponseEntity.created(new URI("/api/tranzacties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tranzacties : Updates an existing tranzactie.
     *
     * @param tranzactieDTO the tranzactieDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tranzactieDTO,
     * or with status 400 (Bad Request) if the tranzactieDTO is not valid,
     * or with status 500 (Internal Server Error) if the tranzactieDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tranzacties")
    @Timed
    public ResponseEntity<TranzactieDTO> updateTranzactie(@RequestBody TranzactieDTO tranzactieDTO) throws URISyntaxException {
        log.debug("REST request to update Tranzactie : {}", tranzactieDTO);
        if (tranzactieDTO.getId() == null) {
            return createTranzactie(tranzactieDTO);
        }
        TranzactieDTO result = tranzactieService.save(tranzactieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tranzactieDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tranzacties : get all the tranzacties.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tranzacties in body
     */
    @GetMapping("/tranzacties")
    @Timed
    public ResponseEntity<List<TranzactieDTO>> getAllTranzacties(Pageable pageable) {
        log.debug("REST request to get a page of Tranzacties");
        Page<TranzactieDTO> page = tranzactieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tranzacties");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tranzacties/:id : get the "id" tranzactie.
     *
     * @param id the id of the tranzactieDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tranzactieDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tranzacties/{id}")
    @Timed
    public ResponseEntity<TranzactieDTO> getTranzactie(@PathVariable Long id) {
        log.debug("REST request to get Tranzactie : {}", id);
        TranzactieDTO tranzactieDTO = tranzactieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tranzactieDTO));
    }

    /**
     * DELETE  /tranzacties/:id : delete the "id" tranzactie.
     *
     * @param id the id of the tranzactieDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tranzacties/{id}")
    @Timed
    public ResponseEntity<Void> deleteTranzactie(@PathVariable Long id) {
        log.debug("REST request to delete Tranzactie : {}", id);
        tranzactieService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
