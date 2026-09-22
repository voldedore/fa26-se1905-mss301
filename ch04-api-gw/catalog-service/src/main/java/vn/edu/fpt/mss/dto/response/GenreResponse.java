package vn.edu.fpt.mss.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Genre representation")
public class GenreResponse {

    @Schema(example = "1")
    private Integer genreId;

    @Schema(example = "Rock")
    private String name;
}