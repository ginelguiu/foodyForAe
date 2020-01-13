package org.foody.repository;

import org.foody.domain.Comentariu;
import org.foody.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;


@SuppressWarnings("unused")
@Repository
public interface ComentariuRepository extends JpaRepository<Comentariu, Long> {

    @Query("select comentariu from Comentariu comentariu where comentariu.user.login = ?#{principal.username}")
    List<Comentariu> findByUserIsCurrentUser();

    Page<Comentariu> findComentariusByReview(Pageable pageable, Review review);
}
