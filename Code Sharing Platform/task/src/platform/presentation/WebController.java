package platform.presentation;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.business.entity.Snippet;
import platform.business.service.SnippetService;

import java.util.ArrayList;

@Controller
public class WebController {

    private final SnippetService snippetService;

    public WebController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String codeHtml(Model model, @PathVariable String id) {
        Snippet snippet = snippetService.getSnippetById(id);
        model.addAttribute("snippet", snippet);
        return "code";
    }

    @GetMapping(value = "/code/latest", produces = MediaType.TEXT_HTML_VALUE)
    public String codeHtmlLatest(Model model) {
        ArrayList<Snippet> snippets = new ArrayList<>(snippetService.getLatestSnippets());
        model.addAttribute("snippets", snippets);
        return "latest";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String newSnippetHtml() {
        return "new_code";
    }
}
