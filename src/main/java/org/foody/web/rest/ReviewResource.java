package org.foody.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.foody.repository.CategorieRepository;
import org.foody.repository.ReviewRepository;
import org.foody.repository.UserRepository;
import org.foody.service.ReviewService;
import org.foody.web.rest.errors.BadRequestAlertException;
import org.foody.web.rest.util.HeaderUtil;
import org.foody.web.rest.util.PaginationUtil;
import org.foody.service.dto.ReviewDTO;
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
public class ReviewResource {

    private final Logger log = LoggerFactory.getLogger(ReviewResource.class);

    private static final String ENTITY_NAME = "review";

    private final ReviewService reviewService;

    private final ReviewRepository reviewRepository;

    private final CategorieRepository categorieRepository;

    private final UserRepository userRepository;

    public ReviewResource(ReviewService reviewService, ReviewRepository reviewRepository, CategorieRepository categorieRepository, UserRepository userRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.categorieRepository = categorieRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/reviews")
    @Timed
    public ResponseEntity<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("Cerere REST pentru a salva un review: {}", reviewDTO);
        if (reviewDTO.getId() != null) {
            throw new BadRequestAlertException("Un review nou nu poate avea un ID", ENTITY_NAME, "idexists");
        }
        reviewDTO.setUserId(userRepository.findOneByLogin(getCurrentUserLogin()).get().getId());
        reviewDTO.setDataReview(LocalDate.now());
        reviewDTO.setModerat(false);
        reviewDTO.setNumarLikeuri(0);
        reviewDTO.setNumarDislikeuri(0);
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.created(new URI("/api/reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/reviews")
    @Timed
    public ResponseEntity<ReviewDTO> updateReview(@Valid @RequestBody ReviewDTO reviewDTO) throws URISyntaxException {
        log.debug("REST request to update Review : {}", reviewDTO);
        if (reviewDTO.getId() == null) {
            return createReview(reviewDTO);
        }
        ReviewDTO result = reviewService.save(reviewDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reviewDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/reviews")
    @Timed
    public ResponseEntity<List<ReviewDTO>> getAllReviews(Pageable pageable) {
        log.debug("REST request to get a page of Reviews");
        Page<ReviewDTO> page = reviewService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reviews");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/reviews/categorie/{id}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByCategorie(Pageable pageable, @PathVariable Long id){
        Page<ReviewDTO> page = reviewService.findAllByCategorie(pageable, categorieRepository.findOne(id));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reviews/categorie/"+id);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/reviews/user/{id}")
    public ResponseEntity<List<ReviewDTO>> getAllReviewsByUser(Pageable pageable, @PathVariable Long id){
        Page<ReviewDTO> page = reviewService.findAllByUser(pageable, userRepository.findOne(id));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reviews/categorie/"+id);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        log.debug("REST request to get Review : {}", id);
        ReviewDTO reviewDTO = reviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reviewDTO));
    }


    @DeleteMapping("/reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        log.debug("REST request to delete Review : {}", id);
        reviewService.delete(id);
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
