package platform.business.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.business.entity.Snippet;
import platform.persistence.SnippetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    public long save(Snippet snippet) {
        var result = snippetRepository.save(snippet);
        return result.getId();
    }

    public Snippet getSnippetById(Long id) {
        Optional<Snippet> foundSnippet = snippetRepository.findSnippetById(id);
        if (foundSnippet.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        else
            return foundSnippet.get();
    }

    public List<Snippet> getLatestSnippets() {
        return snippetRepository.findFirst10ByOrderByDateDesc();
    }
}
