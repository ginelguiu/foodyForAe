package org.foody.service.impl;

import org.foody.domain.Review;
import org.foody.service.ComentariuService;
import org.foody.domain.Comentariu;
import org.foody.repository.ComentariuRepository;
import org.foody.service.dto.ComentariuDTO;
import org.foody.service.mapper.ComentariuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ComentariuServiceImpl implements ComentariuService {

    private final Logger log = LoggerFactory.getLogger(ComentariuServiceImpl.class);

    private final ComentariuRepository comentariuRepository;

    private final ComentariuMapper comentariuMapper;

    public ComentariuServiceImpl(ComentariuRepository comentariuRepository, ComentariuMapper comentariuMapper) {
        this.comentariuRepository = comentariuRepository;
        this.comentariuMapper = comentariuMapper;
    }


    @Override
    public ComentariuDTO save(ComentariuDTO comentariuDTO) {
        log.debug("Request to save Comentariu : {}", comentariuDTO);
        Comentariu comentariu = comentariuMapper.toEntity(comentariuDTO);
        comentariu = comentariuRepository.save(comentariu);
        return comentariuMapper.toDto(comentariu);
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ComentariuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comentarius");
        return comentariuRepository.findAll(pageable)
            .map(comentariuMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public ComentariuDTO findOne(Long id) {
        log.debug("Request to get Comentariu : {}", id);
        Comentariu comentariu = comentariuRepository.findOne(id);
        return comentariuMapper.toDto(comentariu);
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comentariu : {}", id);
        comentariuRepository.delete(id);
    }

    @Override
    public Page<ComentariuDTO> findAllByReview(Pageable pageable, Review review) {
        return comentariuRepository.findComentariusByReview(pageable, review).map(comentariuMapper::toDto);
    }
}
