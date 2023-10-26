package io.github.meritepk.webapp.news.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsReviewRepository extends JpaRepository<NewsReview, Long> {
}
