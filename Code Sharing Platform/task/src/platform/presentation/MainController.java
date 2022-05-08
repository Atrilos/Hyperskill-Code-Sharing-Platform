package platform.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.business.DTO.SnippetDto;
import platform.business.entity.Snippet;
import platform.business.service.SnippetService;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    private final SnippetService snippetService;

    public MainController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping("/code/{id}")
    public Mono<ResponseEntity<SnippetDto>> codeJson(@PathVariable String id) {
        var foundSnippet = snippetService.getSnippetById(id);
        return Mono.just(ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(new SnippetDto(foundSnippet)));
    }

    @GetMapping("code/latest")
    public Mono<ResponseEntity<?>> codeJsonLatest() {
        return Mono.just(ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(snippetService.getLatestSnippets()));
    }

    @PostMapping("/code/new")
    public Mono<ResponseEntity<?>> newSnippet(@RequestBody Snippet snippet) {
        String id = snippetService.save(snippet);
        return Mono.just(ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(Map.of("id", id)));
    }
}
