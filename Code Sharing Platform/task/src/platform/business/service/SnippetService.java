package platform.business.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.business.entity.Snippet;
import platform.persistence.SnippetRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SnippetService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public String save(Snippet snippet) {
        snippet.setId(UUID.randomUUID().toString());
        snippet.setDate(LocalDateTime.now().format(formatter));
        if (snippet.getTime() != 0)
            snippet.setTimeRestricted(true);
        if (snippet.getViews() != 0)
            snippet.setViewRestricted(true);
        var result = snippetRepository.save(snippet);
        return result.getId();
    }

    public Snippet getSnippetById(String id) {
        Snippet snippet = snippetRepository.findSnippetById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return checkRestrictions(snippet);
    }

    public List<Snippet> getLatestSnippets() {
        List<Snippet> snippets = new ArrayList<>(snippetRepository.findAll());
        return snippets.stream()
                .filter(s -> s.getTime() == 0 && s.getViews() == 0)
                .sorted(Comparator.comparing(Snippet::getDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    private Snippet checkRestrictions(Snippet snippet) {
        if (!snippet.isTimeRestricted() && !snippet.isViewRestricted())
            return snippet;
        if (snippet.isViewRestricted()) {
            if (snippet.getViews() > 0) {
                snippet.setViews(snippet.getViews() - 1);
                snippetRepository.save(snippet);
            } else {
                snippetRepository.delete(snippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        if (snippet.isTimeRestricted()) {
            var date = LocalDateTime.parse(snippet.getDate(), formatter);
            int elapsedTime = (int) ChronoUnit.SECONDS.between(date, LocalDateTime.now());
            int timeLeft = snippet.getTime() - elapsedTime;
            if (timeLeft > 0) {
                snippet.setTime(timeLeft);
            } else {
                snippetRepository.delete(snippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        return snippet;
    }
}
