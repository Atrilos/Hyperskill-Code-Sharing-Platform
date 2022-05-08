package platform.business.DTO;

import platform.business.entity.Snippet;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record SnippetDto(@NotBlank String code, String date, int time, int views) implements Serializable {

    public SnippetDto(Snippet snippet) {
        this(snippet.getCode(), snippet.getDate(), snippet.getTime(), snippet.getViews());
    }
}
