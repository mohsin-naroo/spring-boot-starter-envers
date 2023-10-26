package io.github.meritepk.webapp.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @EntityGraph(attributePaths = { "reviews" })
    Page<News> findByOrderByIdDesc(Pageable pageable);
}
