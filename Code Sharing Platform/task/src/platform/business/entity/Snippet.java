package platform.business.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Snippet {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @NotBlank
    @Lob
    @Column(name = "code")
    private String code;

    private String date;

    @Column(name = "time", columnDefinition = "integer default 0")
    private int time = 0;

    @Column(name = "views", columnDefinition = "integer default 0")
    private int views = 0;

    @Column(columnDefinition = "boolean default false")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isTimeRestricted = false;

    @Column(columnDefinition = "boolean default false")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isViewRestricted = false;

    public Snippet(String code, int time, int views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }
}
