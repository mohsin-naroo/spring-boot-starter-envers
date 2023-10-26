package io.github.meritepk.webapp.news.media;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMediaRepository extends JpaRepository<NewsMedia, Long> {
}
