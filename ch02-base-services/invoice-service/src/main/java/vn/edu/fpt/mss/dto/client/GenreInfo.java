package vn.edu.fpt.mss.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Genre data fetched from catalog-service")
public class GenreInfo {

    @Schema(example = "1")
    private Integer genreId;

    @Schema(example = "Rock")
    private String name;
}