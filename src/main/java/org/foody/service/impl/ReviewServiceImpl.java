package org.foody.service.impl;

import org.foody.domain.Categorie;
import org.foody.domain.User;
import org.foody.service.ReviewService;
import org.foody.domain.Review;
import org.foody.repository.ReviewRepository;
import org.foody.service.dto.ReviewDTO;
import org.foody.service.mapper.ReviewMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }


    @Override
    public ReviewDTO save(ReviewDTO reviewDTO) {
        log.debug("Request to save Review : {}", reviewDTO);
        Review review = reviewMapper.toEntity(reviewDTO);
        review = reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    @Override
    public Page<ReviewDTO> findAllByCategorie(Pageable pageable, Categorie categorie) {
        return reviewRepository.findReviewsByCategorie(pageable, categorie).map(reviewMapper::toDto);
    }

    @Override
    public Page<ReviewDTO> findAllByUser(Pageable pageable, User user) {
        return reviewRepository.findReviewsByUser(pageable, user).map(reviewMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ReviewDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reviews");
        return reviewRepository.findAll(pageable)
            .map(reviewMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public ReviewDTO findOne(Long id) {
        log.debug("Request to get Review : {}", id);
        Review review = reviewRepository.findOne(id);
        return reviewMapper.toDto(review);
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete Review : {}", id);
        reviewRepository.delete(id);
    }
}
