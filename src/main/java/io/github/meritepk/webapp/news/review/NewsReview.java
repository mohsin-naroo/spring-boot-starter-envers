package io.github.meritepk.webapp.news.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.meritepk.webapp.news.News;

@Audited
@Entity
public class NewsReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String review;

    @JsonIgnore
    @ManyToOne
    private News news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public long getNewsId() {
        return this.news.getId();
    }
}
