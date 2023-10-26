package io.github.meritepk.webapp.news;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping(value = { "/api/v1/news" })
    public ResponseEntity<Page<News>> get(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "100") int size) {
        Page<News> news = service.find(page, size);
        return ResponseEntity.ok(news);
    }

    @PostMapping(value = { "/api/v1/news" })
    public ResponseEntity<News> post(@RequestBody News news) {
        News saved = service.create(news);
        return ResponseEntity.ok(saved);
    }

    @PostMapping(value = { "/api/v1/news/{id}" })
    public ResponseEntity<News> post(@PathVariable(name = "id") Long id, @RequestBody News news) {
        news.setId(id);
        News saved = service.update(news);
        if (saved != null) {
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = { "/api/v1/news/{id}" })
    public ResponseEntity<News> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
