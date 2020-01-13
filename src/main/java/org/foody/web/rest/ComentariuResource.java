package org.foody.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.foody.domain.Comentariu;
import org.foody.domain.User;
import org.foody.repository.ReviewRepository;
import org.foody.repository.UserRepository;
import org.foody.service.ComentariuService;
import org.foody.web.rest.errors.BadRequestAlertException;
import org.foody.web.rest.util.HeaderUtil;
import org.foody.web.rest.util.PaginationUtil;
import org.foody.service.dto.ComentariuDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ComentariuResource {

    private final Logger log = LoggerFactory.getLogger(ComentariuResource.class);

    private static final String ENTITY_NAME = "comentariu";

    private final ComentariuService comentariuService;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    public ComentariuResource(ComentariuService comentariuService, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.comentariuService = comentariuService;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/comentarii")
    @Timed
    public ResponseEntity<ComentariuDTO> createComentariu(@Valid @RequestBody ComentariuDTO comentariuDTO) throws URISyntaxException {
        log.debug("REST request to save Comentariu : {}", comentariuDTO);
        if (comentariuDTO.getId() != null) {
            throw new BadRequestAlertException("A new comentariu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        User user = new User();
        user= userRepository.findOneByLogin(getCurrentUserLogin()).get();
        comentariuDTO.setUserId(user.getId());
        comentariuDTO.setDataComentariu(LocalDate.now());
        ComentariuDTO result = comentariuService.save(comentariuDTO);
        return ResponseEntity.created(new URI("/api/comentarii/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/comentarii")
    @Timed
    public ResponseEntity<ComentariuDTO> updateComentariu(@Valid @RequestBody ComentariuDTO comentariuDTO) throws URISyntaxException {
        log.debug("REST request to update Comentariu : {}", comentariuDTO);
        if (comentariuDTO.getId() == null) {
            return createComentariu(comentariuDTO);
        }
        ComentariuDTO result = comentariuService.save(comentariuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, comentariuDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/comentarii")
    @Timed
    public ResponseEntity<List<ComentariuDTO>> getAllComentarius(Pageable pageable) {
        log.debug("REST request to get a page of comentarii");
        Page<ComentariuDTO> page = comentariuService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comentarii");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/comentarii/{id}")
    @Timed
    public ResponseEntity<ComentariuDTO> getComentariu(@PathVariable Long id) {
        log.debug("REST request to get Comentariu : {}", id);
        ComentariuDTO comentariuDTO = comentariuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(comentariuDTO));
    }

    @GetMapping("/comentarii/review/{id}")
    public ResponseEntity<List<ComentariuDTO>> getAllComentariusByReview(Pageable pageable, @PathVariable Long id){
        Page<ComentariuDTO> page = comentariuService.findAllByReview(pageable, reviewRepository.findOne(id));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comentarii/review/"+id);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    @DeleteMapping("/comentarii/{id}")
    @Timed
    public ResponseEntity<Void> deleteComentariu(@PathVariable Long id) {
        log.debug("REST request to delete Comentariu : {}", id);
        comentariuService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    public String getCurrentUserLogin() {
        org.springframework.security.core.context.SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                login = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                login = (String) authentication.getPrincipal();

        return login;
    }
}
