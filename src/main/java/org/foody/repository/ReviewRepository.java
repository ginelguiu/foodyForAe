package org.foody.repository;

import org.foody.domain.Categorie;
import org.foody.domain.Review;
import org.foody.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;


@SuppressWarnings("unused")
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select review from Review review where review.user.login = ?#{principal.username}")
    List<Review> findByUserIsCurrentUser();

    Page<Review> findReviewsByCategorie(Pageable pageable, Categorie categorie);
    Page<Review> findReviewsByUser(Pageable pageable, User user);
}
