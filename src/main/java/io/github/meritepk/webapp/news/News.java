package io.github.meritepk.webapp.news;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import io.github.meritepk.webapp.news.media.NewsMedia;
import io.github.meritepk.webapp.news.review.NewsReview;

@Audited
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private LocalDateTime reportedAt;

    @NotAudited
    @OneToMany(mappedBy = "news", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<NewsMedia> media;

    @OneToMany(mappedBy = "news", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<NewsReview> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public List<NewsMedia> getMedia() {
        return media;
    }

    public void setMedia(List<NewsMedia> media) {
        this.media = media;
    }

    public List<NewsReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<NewsReview> reviews) {
        this.reviews = reviews;
    }
}
