package io.github.meritepk.webapp.news;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.meritepk.webapp.news.media.NewsMedia;
import io.github.meritepk.webapp.news.review.NewsReview;

@Service
public class NewsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Page<News> find(int page, int size) {
        logger.debug("find");
        Page<News> news = repository.findByOrderByIdDesc(PageRequest.of(page, size));
        news.getContent().forEach(value -> value.getMedia().size());
        return news;
    }

    public Optional<News> find(Long id) {
        logger.debug("find");
        return repository.findById(id);
    }

    @Transactional
    public News create(News news) {
        NewsMedia media = new NewsMedia();
        media.setLink("link");
        media.setNews(news);

        ArrayList<NewsReview> reviews = new ArrayList<>();
        NewsReview one = new NewsReview();
        one.setReview("one");
        one.setNews(news);
        reviews.add(one);
        NewsReview two = new NewsReview();
        two.setReview("two");
        two.setNews(news);
        reviews.add(two);

        news.setMedia(List.of(media));
        news.setReviews(reviews);
        News saved = repository.save(news);
        return saved;
    }

    @Transactional
    public News update(News news) {
        Optional<News> optional = repository.findById(news.getId());
        News saved = null;
        if (optional.isPresent()) {
            saved = optional.get();

            List<NewsReview> reviews = saved.getReviews();
            reviews.remove(reviews.size() - 1);
            reviews.get(0).setReview(reviews.get(0).getReview() + "-" + reviews.get(0).getReview());
            NewsReview three = new NewsReview();
            three.setReview("three");
            three.setNews(saved);
            reviews.add(three);

            saved.setTitle(news.getTitle());
            saved.setDetails(news.getDetails());
            saved.setReportedAt(news.getReportedAt());
            saved = repository.save(saved);
            saved.getMedia().size();
        }
        return saved;
    }

    public void delete(Long newsId) {
        repository.deleteById(newsId);
    }
}
